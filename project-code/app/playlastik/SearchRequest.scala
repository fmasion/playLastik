package playlastik

import com.sksamuel.elastic4s.ElasticDsl._
import play.api.Logger
import play.api.libs.json.Json
import playlastik.models.SearchResponse
import playlastik.dslHelper.SearchHelper
import play.api.libs.concurrent.Execution.Implicits.defaultContext

import scala.concurrent.Future


trait SearchRequest {
  this: WSimpl =>

  def execute(req: SearchDefinition) = search(req)

  def search(req: SearchDefinition): Future[SearchResponse] = {
    val reqInfo = SearchHelper.getRequestInfo(serviceUrl, req)
    val wsResp = doCall(reqInfo)
    wsResp.map(r => Json.parse(r.body)).map{j =>
      //Logger.error(""+j)
      j.as[SearchResponse]
    }
  }

//  def execute(searches: MultiSearchRequest): Future[MultiSearchResponse] =
//    injectFuture[MultiSearchResponse](client.multiSearch(searches, _))
//  def execute(searches: MultiSearchDefinition): Future[MultiSearchResponse] = execute(searches.build)
//  def execute(searches: SearchDefinition*): Future[MultiSearchResponse] = execute(new MultiSearchDefinition(searches))
//  def execute(req: MultiGetDefinition): Future[MultiGetResponse] = injectFuture[MultiGetResponse](client.multiGet(req.build, _))

//  def execute(req: MoreLikeThisRequest): Future[SearchResponse] = injectFuture[SearchResponse](client.moreLikeThis(req, _))
//  def execute(req: MoreLikeThisDefinition): Future[SearchResponse] = execute(req.build)


}
