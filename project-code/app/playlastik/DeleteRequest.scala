package playlastik

import com.sksamuel.elastic4s.ElasticDsl._
import playlastik.dslHelper.DeleteHelper


trait DeleteRequest { this: WSimpl =>

  def delete(req: DeleteByIdDefinition) = {
    val reqInfo = DeleteHelper.getRequestInfo(serviceUrl, req)
    doCall(reqInfo)
  }

  def delete(req: DeleteByQueryDefinition) = {
    val reqInfo = DeleteHelper.getRequestInfo(serviceUrl, req)
    doCall(reqInfo)
  }

}
