package playlastik

import com.sksamuel.elastic4s.GetDefinition
import playlastik.dslHelper.GetHelper
import playlastik.models.GetResponse
import play.api.libs.concurrent.Execution.Implicits.defaultContext


trait GetRequest {
  this: WSimpl =>

  def execute(req: GetDefinition) = get(req)

  def getSource(req: GetDefinition) = get(req = req, source = true)

  def get(req: GetDefinition, source: Boolean) = {
    val reqInfo = GetHelper.getRequestInfo(serviceUrl, req, source)
    val wsResp = doCall(reqInfo)
    wsResp.map{j =>
      j.as[GetResponse]
    }
  }

  def get(gets: GetDefinition*) = {
    val reqInfo = GetHelper.getRequestInfo(serviceUrl, gets)
    val wsResp = doCall(reqInfo)
    wsResp.map{j =>
      //log.error("" + j)
      j.as[GetResponse]
    }
  }

}
