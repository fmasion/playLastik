package playlastik.dslHelper

import com.sksamuel.elastic4s._
import com.sksamuel.elastic4s.ElasticDsl._
import org.elasticsearch.action.deletebyquery.QueryRequestImplicit._
import com.sksamuel.elastic4s.Implicits._
import play.api.libs.json._
import playlastik.Get

object GetHelper {
  
  def getRequestInfo(serviceUrl: String, req: GetDefinition, source:Boolean=false): RequestInfo = {
      val index = "/" + req.build.index()
    val esType = "/" + req.build.`type`()
    val id = "/" + req.build.id()
    val url = serviceUrl + index + esType + id + (if (source) ("/_source") else "")
    
    val fields = req.build.fields()

    // handle parent / operation / routing / version in query parameter
    val lOptQueryParams: List[Option[(String, String)]] = (
      Option(req.build.routing()).map(r => "routing" -> r) ::
      Nil)

    val queryParams = lOptQueryParams flatMap (_.toList)

    val queryParamsWithFields = if (fields.isEmpty) {
      queryParams
    } else {
      ("fields" -> fields.mkString(",")) :: queryParams
    }
    
    RequestInfo(Get, url,"", queryParamsWithFields)
  } 

  def getRequestInfo(serviceUrl: String, gets: Seq[GetDefinition]): RequestInfo = {
     def getJson(req: GetDefinition): JsObject = {
      val data = Json.obj("_index" -> req.build.index(),
        "_type" -> req.build.`type`(),
        "_id" -> req.build.id())

      val fields = req.build.fields()
      val fieldProp = if (fields.isEmpty) {
        Json.obj()
      } else {
        Json.obj("fields" -> fields.mkString(","))
      }
      data ++ fieldProp
    }
    val docs = Json.obj("docs" -> JsArray(for (req <- gets) yield (getJson(req))))
    val url = serviceUrl + "/_mget"
    
    RequestInfo(Get, url,docs.toString)
  } 
  
  
}