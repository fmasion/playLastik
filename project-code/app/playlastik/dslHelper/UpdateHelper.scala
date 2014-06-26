package playlastik.dslHelper

import com.sksamuel.elastic4s._
import com.sksamuel.elastic4s.ElasticDsl._
import org.elasticsearch.action.deletebyquery.QueryRequestImplicit._
import com.sksamuel.elastic4s.Implicits._
import play.api.libs.json._
import playlastik._


object UpdateHelper {
  val action = "_update"

  def getRequestInfo(serviceUrl: String, req: UpdateDefinition): RequestInfo = {
    val index = "/" + req.build.index()
    val esType = "/" + req.build.`type`()
    val id = "/" + req.build.id() + "/"
    val url = serviceUrl + index + esType + id + action

    // handle parent / operation / routing / version in query parameter
    val lOptQueryParams: List[Option[(String, String)]] = (
      req.oRouting.map(r => "routing" -> r) ::
      req.oRefresh.map(r => "refresh" -> ("" + r)) ::
      req.oFields.map(t => "fields" -> ("" + t)) ::
      req.oRetryOnConflict.map(t => "retry_on_conflict" -> ("" + t)) ::
      Nil)
    val queryParams = lOptQueryParams flatMap (_.toList)

    val lOptBody: List[Option[(String, JsValue)]] = (
      req.oScript.map(r => "script" -> JsString(r)) ::
      req.oScriptParams.map(r => "params" -> JsString(r)) ::
      req.oUpsertRequest.map(r => "upsert" -> JsString(r)) ::
      Nil)
    val bodyParams = lOptBody flatMap (_.toList)
    val body = Json.toJson(Map[String, JsValue]() ++ bodyParams)

    RequestInfo(Put, url, body.toString, queryParams)
  }

  /**
   * Bulk index or create
   */
  def getBulk(bulk: UpdateDefinition): List[String] = {
    getAction_meta_data(bulk).toString :: getBody(bulk).toString :: Nil
  }

  private def getAction_meta_data(bulk: UpdateDefinition): JsObject = {
    val index = "/" + bulk.build.index()
    val esType = "/" + bulk.build.`type`()

    val loAction: List[Option[(String, JsValue)]] = (
      Option("_index" -> JsString(bulk.build.index())) ::
      Option("_type" -> JsString(bulk.build.`type`())) ::
      bulk.oId.map(id => ("_id" -> JsString(id))) ::
      bulk.oRouting.map(r => ("_routing" -> JsString(r))) ::
      bulk.oRefresh.map(r => ("_refresh" -> JsBoolean(r))) ::
      bulk.oRetryOnConflict.map(t => ("_ttl" -> JsNumber(t))) ::
      Nil)

    val lAction = loAction flatMap (_.toList)
    val mAction: Map[String, JsValue] = Map[String, JsValue]() ++ (lAction)

    Json.obj(bulk.action.name() -> Json.toJson(mAction))
  }

  private def getBody(bulk: UpdateDefinition): JsValue = {
    val lOptBody: List[Option[(String, JsValue)]] = (
      bulk.oScript.map(r => "script" -> JsString(r)) ::
      bulk.oScriptParams.map(r => "params" -> JsString(r)) ::
      bulk.oUpsertRequest.map(r => "upsert" -> JsString(r)) ::
      Nil)
    val bodyParams = lOptBody flatMap (_.toList)
    Json.toJson(Map[String, JsValue]() ++ bodyParams)
  }

}