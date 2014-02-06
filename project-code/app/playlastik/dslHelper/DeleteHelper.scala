package playlastik.dslHelper

import com.sksamuel.elastic4s._
import com.sksamuel.elastic4s.ElasticDsl._
import org.elasticsearch.action.deletebyquery.QueryRequestImplicit._
import com.sksamuel.elastic4s.Implicits._
import play.api.libs.json.Json
import play.api.libs.json.JsObject
import playlastik.Delete

object DeleteHelper {
  val action = "_delete"

  def getRequestInfo(serviceUrl: String, req: DeleteByIdDefinition): RequestInfo = {
    val index = "/" + req.build.index()
    val esType = "/" + req.build.`type`()
    val id = "/" + req.build.id() + "/"
    val url = serviceUrl + index + esType + id 

    val lOptQueryParams: List[Option[(String, String)]] = (
      Option(req.build.routing()).map(r => "routing" -> r) ::
      Nil)
    val queryParams = lOptQueryParams flatMap (_.toList)

    RequestInfo(Delete, url, "", queryParams)
  }

  def getRequestInfo(serviceUrl: String, req: DeleteByQueryDefinition): RequestInfo = {
    val indices = if (req.build.indices().isEmpty || req.build.indices().contains("*")) "/_all" else "/" + req.build.indices().mkString(",")
    val typeList = if (req.build.typeList.isEmpty || req.build.typeList.contains("*")) "" else "/" + req.build.typeList.mkString(",")
    val query = req.build.queryString

    val url = serviceUrl + indices + typeList + "/_query"
    val lOptQueryParams = (
      Option(req.build.routing()).map(r => "routing" -> r) ::
      Nil)
    val queryParams = lOptQueryParams flatMap (_.toList)
    RequestInfo(Delete, url, query, queryParams)

  }

  def getBulk(bulk: DeleteByIdDefinition): List[String] = {
    getAction_meta_data(bulk).toString :: Nil
  }

  private def getAction_meta_data(bulk: DeleteByIdDefinition): JsObject = {
    Json.obj(bulk.action.name() ->
      Json.obj("_index" -> bulk.build.index(),
        "_type" -> bulk.build.`type`(),
        "_id" -> bulk.build.id()))
  }

}