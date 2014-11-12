package playlastik

import com.sksamuel.elastic4s.CountDsl
import play.api.libs.json.Json
import playlastik.dslHelper.CountHelper
import com.sksamuel.elastic4s.ElasticDsl._
import playlastik.models.CountResponse
import play.api.libs.concurrent.Execution.Implicits.defaultContext

import scala.concurrent.Future

//TODO
trait CountRequest {
  this: WSimpl =>

  def execute(req: CountDefinition):Future[CountResponse] = count(req)

  def count(req: CountDefinition) = {
    val reqInfo = CountHelper.getRequestInfo(serviceUrl, req)
    val wsResp = doCall(reqInfo)
    wsResp.map(r => Json.parse(r.body)).map(j => (j.as[CountResponse]))
  }

}
