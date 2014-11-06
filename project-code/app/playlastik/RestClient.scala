package playlastik

import com.sksamuel.elastic4s.ElasticDsl._
import com.sksamuel.elastic4s._
import play.api.libs.ws.WSResponse
import play.api.{Logger, _}
import play.api.libs.json._
import playlastik.dslHelper._
import playlastik.models._
import play.api.libs.concurrent.Execution.Implicits.defaultContext

import scala.concurrent.Future

object RestClient extends WSimpl with SearchRequest with IndexRequest with GetRequest with DeleteRequest with BulkRequest {


  val log = Logger("playlastik.RestClient")






  //    def deleteIndex(deleteIndex: DeleteIndexDefinition)(implicit duration: Duration): DeleteIndexResponse =
  //      ??? //Await.result(client.deleteIndex(deleteIndex), duration)
  //
  //    def percolate(percolateDef: PercolateDefinition)(implicit duration: Duration): PercolateResponse =
  //      ??? //Await.result(client.percolate(percolateDef), duration)
  //
  //    def register(registerDef: RegisterDefinition)(implicit duration: Duration): IndexResponse =
  //      ??? //Await.result(client.register(registerDef), duration)
  //    def optimize(o: OptimizeDefinition)(implicit duration: Duration): OptimizeResponse =
  //      ??? //Await.result(client.optimize(o), duration)
  //
  //    def exists(indexes: String*): IndicesExistsResponse = ??? //Await.result(client.exists(indexes: _*), duration)




  object Admin {
    def stats: Future[StatsResponse] = {
      doCall(RequestInfo(Get, (serviceUrl + "/_stats"), "")).map { r =>
        val jbody = Json.parse(r.body)
        jbody.as[StatsResponse]
      }
    }
  }

}
