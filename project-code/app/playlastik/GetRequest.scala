package playlastik

import com.sksamuel.elastic4s.{GetDefinition, MultiGetDefinition}
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json.{JsObject, Reads}
import playlastik.dslHelper.GetHelper
import playlastik.models.MultiGetResponse

import scala.concurrent.Future


trait GetRequest {
  this: WSimpl =>

//  def getSource(req: GetDefinition) = get(req = req, source = true)

  def execute(req: GetDefinition, source: Boolean = false): Future[JsObject] = {
    val reqInfo = GetHelper.getRequestInfo(serviceUrl, req, source)
    //log.error(""+reqInfo)
    val wsResp = doCall(reqInfo)
    wsResp.map{j =>
      j.as[JsObject]
    }
  }

  def execute[T](req: GetDefinition)(implicit reads: Reads[T]): Future[Option[T]] = {
    val reqInfo = GetHelper.getRequestInfo(serviceUrl, req, false)
    doCall(reqInfo).map(_.asOpt[T])
  }

  def execute(gets: MultiGetDefinition) = {
    val reqInfo = GetHelper.getRequestInfo(serviceUrl, gets)
    val wsResp = doCall(reqInfo)
    //log.error(""+reqInfo)
    wsResp.map{j =>
      //log.error("" + j)
      j.as[MultiGetResponse]
    }
  }

}
