package playlastik


import com.sksamuel.elastic4s.BulkCompatibleDefinition
import playlastik.dslHelper.BulkHelper


trait BulkRequest {
  this: WSimpl =>


  def bulk(reqs: BulkCompatibleDefinition*) = {
    val reqInfo = BulkHelper.getRequestInfo(serviceUrl, reqs: _*)
    doCall(reqInfo)
  }

//  def execute(bulk: BulkDefinition): Future[BulkResponse] = {injectFuture[BulkResponse](client.bulk(bulk._builder, _))}


}
