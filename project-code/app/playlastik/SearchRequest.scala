package playlastik

import com.sksamuel.elastic4s.ElasticDsl._
import play.api.libs.ws.WSResponse
import playlastik.dslHelper.SearchHelper

import scala.concurrent.Future


trait SearchRequest { this: WSimpl =>


  def execute(req: SearchDefinition) = search(req)

  // TODO Future[Response]
  def search(req: SearchDefinition): Future[WSResponse] = {
    val reqInfo = SearchHelper.getRequestInfo(serviceUrl, req)
    doCall(reqInfo)
  }



}
