package playlastik.dslHelper

import com.sksamuel.elastic4s._
import com.sksamuel.elastic4s.ElasticDsl._
import org.elasticsearch.action.deletebyquery.QueryRequestImplicit._
import com.sksamuel.elastic4s.Implicits._
import play.api.libs.json._
import playlastik._

object IndexHelper {
  val action = "_index"

  def getRequestInfo(serviceUrl: String, req: IndexDefinition): RequestInfo = {
    val index = "/" + req.build.index()
    val esType = "/" + req.build.`type`()
    val id = req.oId.map("/" + _ + "/").getOrElse("/")
    val url = serviceUrl + index + esType + id

    // handle parent / operation / routing / version in query parameter
    val lOptQueryParams: List[Option[(String, String)]] = (
      req.oParent.map(p => "parent" -> p) ::
      req.oVersion.map(v => "version" -> ("" + v)) ::
      req.oVersionType.map(vt => "version_type" -> vt) ::
      //req.oOpType.map(t => "op_type" -> t) ::
      req.oRouting.map(r => "routing" -> r) ::
      req.oPercolate.map(p => "percolate" -> p) ::
      req.oTtl.map(t => "ttl" -> (""+t)) ::
      req.oTimeStamp.map(t => "timestamp" -> t) ::
      req.oRefresh.map(r => "refresh" -> (""+r)) ::    
      Nil)

    val queryParams = lOptQueryParams flatMap (_.toList)
    
    val method:Method = req.oId.map(id => Put).getOrElse(Post)

    RequestInfo(method, url, req.queryString, queryParams)
  }

  /**
   * Bulk index or create
   */
  def getBulk(bulk: IndexDefinition): List[String] = {
    getAction_meta_data(bulk).toString :: bulk.queryString :: Nil
  }

  private def getAction_meta_data(bulk: IndexDefinition): JsObject = {
    val index = "/" + bulk.build.index()
    val esType = "/" + bulk.build.`type`()

    val loAction: List[Option[(String, JsValue)]] = (
      Option("_index" -> JsString(bulk.build.index())) ::
      Option("_type" -> JsString(bulk.build.`type`())) ::
      bulk.oId.map(id => ("_id" -> JsString(id))) ::
      bulk.oParent.map(p => ("_parent" -> JsString(p))) ::
      bulk.oVersion.map(v => ("_version" -> JsNumber(v))) ::
      bulk.oVersionType.map(vt => ("_version_type" -> JsString(vt))) ::
      bulk.oRouting.map(r => ("_routing" -> JsString(r))) ::
      bulk.oPercolate.map(p => ("_percolate" -> JsString(p))) ::
      bulk.oRefresh.map(r => ("_refresh" -> JsBoolean(r))) ::
      bulk.oTtl.map(t => ("_ttl" -> JsNumber(t))) ::
      bulk.oTimeStamp.map(t => "_timestamp" -> JsString(t)) ::
      Nil)

    val lAction = loAction flatMap (_.toList)
    val mAction: Map[String, JsValue] = Map[String, JsValue]() ++ (lAction)

    Json.obj(bulk.action.name() -> Json.toJson(mAction))
  }

}