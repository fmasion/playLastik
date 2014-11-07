package playlastik

import com.sksamuel.elastic4s.ElasticDsl._
import play.api.libs.ws.WSResponse
import playlastik.dslHelper.SearchHelper

import scala.concurrent.Future


trait SearchRequest {
  this: WSimpl =>

  def execute(req: SearchDefinition) = search(req)

  // TODO Future[WSResponse] -> Future[SearchResponse]
  def search(req: SearchDefinition): Future[WSResponse] = {
    val reqInfo = SearchHelper.getRequestInfo(serviceUrl, req)
    doCall(reqInfo)
  }

//  def execute(searches: MultiSearchRequest): Future[MultiSearchResponse] =
//    injectFuture[MultiSearchResponse](client.multiSearch(searches, _))
//  def execute(searches: MultiSearchDefinition): Future[MultiSearchResponse] = execute(searches.build)
//  def execute(searches: SearchDefinition*): Future[MultiSearchResponse] = execute(new MultiSearchDefinition(searches))
//  def execute(req: MultiGetDefinition): Future[MultiGetResponse] = injectFuture[MultiGetResponse](client.multiGet(req.build, _))

//  def execute(req: MoreLikeThisRequest): Future[SearchResponse] = injectFuture[SearchResponse](client.moreLikeThis(req, _))
//  def execute(req: MoreLikeThisDefinition): Future[SearchResponse] = execute(req.build)


}
