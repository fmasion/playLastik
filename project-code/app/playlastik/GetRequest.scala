package playlastik

import com.sksamuel.elastic4s.{MultiGetDefinition, GetDefinition}
import playlastik.dslHelper.GetHelper
import playlastik.models.{MultiGetResponse, GetResponse}
import play.api.libs.concurrent.Execution.Implicits.defaultContext


trait GetRequest {
  this: WSimpl =>

//  def getSource(req: GetDefinition) = get(req = req, source = true)

  def execute(req: GetDefinition, source: Boolean = false) = {
    val reqInfo = GetHelper.getRequestInfo(serviceUrl, req, source)
    //log.error(""+reqInfo)
    val wsResp = doCall(reqInfo)
    wsResp.map{j =>
      j.as[GetResponse]
    }
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
