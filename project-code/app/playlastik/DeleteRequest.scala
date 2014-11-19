package playlastik

import com.sksamuel.elastic4s.ElasticDsl._
import playlastik.dslHelper.DeleteHelper
import playlastik.models.DeleteResponse
import play.api.libs.concurrent.Execution.Implicits.defaultContext


trait DeleteRequest {
  this: WSimpl =>

  def execute(req: DeleteByIdDefinition) = delete(req)
  def execute(req: DeleteByQueryDefinition) = delete(req)

  def delete(req: DeleteByIdDefinition) = {
    val reqInfo = DeleteHelper.getRequestInfo(serviceUrl, req)
    val wsResp = doCall(reqInfo)
    wsResp.map{j =>
      j.as[DeleteResponse]
    }
  }

  def delete(req: DeleteByQueryDefinition) = {
    val reqInfo = DeleteHelper.getRequestInfo(serviceUrl, req)
    val wsResp = doCall(reqInfo)
    wsResp.map{j =>
      j.as[DeleteResponse]
    }
  }

}
