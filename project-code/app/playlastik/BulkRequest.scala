package playlastik


import com.sksamuel.elastic4s.{BulkDefinition, BulkCompatibleDefinition}
import play.api.libs.json.Json
import playlastik.dslHelper.BulkHelper
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import playlastik.models.BulkResponse

import scala.concurrent.Future


trait BulkRequest {
  this: WSimpl =>

  def bulk(reqs: BulkCompatibleDefinition*):Future[BulkResponse] = {
    val reqInfo = BulkHelper.getRequestInfo(serviceUrl, reqs: _*)
    //log.error("BULK_REQ"+ reqInfo)
    val resp = doCall(reqInfo)
    resp.map { r =>
      val j = Json.parse(r.body)
      //log.error(""+j)
      j.as[BulkResponse]
    }
  }

//  def bulk(bulk: BulkDefinition) = {
//    val reqInfo = BulkHelper.getRequestInfo(serviceUrl, reqs: _*)
//    doCall(reqInfo)
//  }
//  def execute(bulk: BulkDefinition): Future[BulkResponse] = {injectFuture[BulkResponse](client.bulk(bulk._builder, _))}


}
