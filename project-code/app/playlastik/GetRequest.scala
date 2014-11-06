package playlastik

import com.sksamuel.elastic4s.GetDefinition
import playlastik.dslHelper.GetHelper


trait GetRequest { this: WSimpl =>

  def execute(req: GetDefinition) = get(req)

  def getSource(req: GetDefinition) = get(req=req, source=true)

  def get(req: GetDefinition, source: Boolean) = {
    val reqInfo = GetHelper.getRequestInfo(serviceUrl, req, source)
    doCall(reqInfo)
  }

  def get(gets: GetDefinition*) = {
    val reqInfo = GetHelper.getRequestInfo(serviceUrl, gets)
    doCall(reqInfo)
  }

}
