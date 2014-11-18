package playlastik

import play.api.Logger
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json._
import playlastik.admin.{ClusterMgt, PercolatorMgt, IndicesMgt, AliasMgt}
import playlastik.dslHelper._
import playlastik.method.Get
import playlastik.models.StatsResponse

import scala.concurrent.Future

object RestClient extends WSimpl
with SearchRequest
with IndexRequest
with GetRequest
with DeleteRequest
with BulkRequest
with CountRequest
with AliasMgt
with IndicesMgt
with PercolatorMgt
with ClusterMgt {

  val log = Logger("playlastik.RestClient")


  object Admin {
    def stats: Future[StatsResponse] = {
      doCall(RequestInfo(Get, (serviceUrl + "/_stats"), "")).map { j =>
        j.as[StatsResponse]
      }
    }
  }

}
