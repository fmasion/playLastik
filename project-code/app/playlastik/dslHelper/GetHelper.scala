package playlastik.dslHelper

import com.sksamuel.elastic4s._
import org.elasticsearch.action.get.MultiGetRequest
import play.api.Logger
import play.api.libs.json._
import playlastik.method.Get

object GetHelper {

  val log = Logger("playlastik.dslHelper.GetHelper")

  def getRequestInfo(serviceUrl: String, req: GetDefinition, source: Boolean = false): RequestInfo = {
    val index = "/" + req.build.index()
    val esType = "/" + req.build.`type`()
    val id = "/" + req.build.id()
    val url = serviceUrl + index + esType + id + (if (source) ("/_source") else "")

    val ofields = Option(req.build.fields())

    // handle parent / operation / routing / version in query parameter
    val lOptQueryParams: List[Option[(String, String)]] = (
      Option(req.build.routing()).map(r => "routing" -> r) ::
        Nil)

    val queryParams = lOptQueryParams flatMap (_.toList)

    val queryParamsWithFields = if (ofields.filterNot(_.isEmpty).isEmpty) {
      queryParams
    } else {
      ("fields" -> ofields.get.mkString(",")) :: queryParams
    }
    RequestInfo(Get, url, "", queryParamsWithFields)
  }

  def getRequestInfo(serviceUrl: String, multigets: MultiGetDefinition): RequestInfo = {
    import scala.collection.JavaConversions._
    def getJson(req: MultiGetRequest.Item): JsObject = {
      val data = Json.obj("_index" -> req.index(),
        "_type" -> req.`type`(),
        "_id" -> req.id())

      val fields = Option(req.fields()).filterNot(_.isEmpty)
      //log.error("FIELDS " + fields)
      val fieldProp = fields.map(optfields => Json.obj("fields" -> optfields.mkString(","))).getOrElse(Json.obj())
      data ++ fieldProp
    }
    val docs = Json.obj("docs" -> JsArray(for (req <- multigets.build.getItems) yield (getJson(req))))
    val url = serviceUrl + "/_mget"

    RequestInfo(Get, url, docs.toString)
  }


}