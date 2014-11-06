package playlastik

import com.sksamuel.elastic4s.ElasticDsl._
import play.api.libs.json.Json
import playlastik.dslHelper.IndexHelper
import playlastik.models.IndexResponse
import play.api.libs.concurrent.Execution.Implicits.defaultContext

import scala.concurrent.Future


trait IndexRequest { this: WSimpl =>

  def execute(req: IndexDefinition) = index(req)

  def index(req: IndexDefinition): Future[IndexResponse] = {
    val reqInfo = IndexHelper.getRequestInfo(serviceUrl, req)
    val wsResp = doCall(reqInfo)
    wsResp.map(r => Json.parse(r.body)).map(j => (j.as[IndexResponse]))
  }

}
