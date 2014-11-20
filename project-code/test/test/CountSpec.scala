package test

import com.sksamuel.elastic4s.ElasticDsl._
import org.junit.runner._
import org.specs2.mutable._
import org.specs2.runner._
import play.api.Logger
import play.api.test.{WithApplication, _}
import playlastik._

import scala.concurrent.Await
import scala.concurrent.duration._

@RunWith(classOf[JUnitRunner])
class CountSpec extends Specification with PlaySpecification {
  val log = Logger("test.CountSpec")
  sequential

  "A count request" should {
    "return total count when no query is specified" in new WithApplication(FakeApplication(additionalPlugins = Seq("playlastik.plugin.PlayLastiKPlugin"), additionalConfiguration = Map("playLastiK.isDevMode" -> true, "playLastiK.cleanOnStop" -> true, "playLastik.cluster.name" -> "count"))) {
      CountSpec.initStep
//      val countResponse = Await.result(RestClient.execute(count from "_all"), Duration(2, "second"))
      val countResponse1 = Await.result(RestClient.execute(count from Seq("london", "paris")), Duration(2, "second"))
      val countResponse2 = Await.result(RestClient.execute(count from "london"), Duration(2, "second"))
      val countResponse3 = Await.result(RestClient.execute(count from "london"->"landmarks"), Duration(2, "second"))
//      countResponse.count must beEqualTo(5)
      countResponse1.count must beEqualTo(4)
      countResponse2.count must beEqualTo(3)
      countResponse3.count must beEqualTo(2)
    }
  }

  "A count request" should {
    "test bulk" in new WithApplication(FakeApplication(additionalPlugins = Seq("playlastik.plugin.PlayLastiKPlugin"), additionalConfiguration = Map("playLastiK.isDevMode" -> true, "playLastiK.cleanOnStop" -> true, "playLastik.cluster.name" -> "countBulk"))) {
      CountSpec.initStep2
//      val searchResponse = Await.result(RestClient.execute(search in "_all"), Duration(2, "second"))
//      log.error(""+searchResponse)
//      val countResponse = Await.result(RestClient.execute(count from "_all"), Duration(2, "second"))
      val countResponse1 = Await.result(RestClient.execute(count from Seq("waterways", "waterways_updated")), Duration(2, "second"))
      val countResponse2 = Await.result(RestClient.execute(count from "waterways"), Duration(2, "second"))
      val countResponse3 = Await.result(RestClient.execute(count from "waterways"->"rivers"), Duration(2, "second"))
//      countResponse.count must beEqualTo(4)
      countResponse1.count must beEqualTo(4)
      countResponse2.count must beEqualTo(3)
      countResponse3.count must beEqualTo(2)
    }
  }

}


object CountSpec {
  def initStep = {
    val res = Await.result(RestClient.execute(index into "london/landmarks" fields "name" -> "hampton court palace"), Duration(2, "second"))
    val res2 = Await.result(RestClient.execute(index into "london/landmarks" fields "name" -> "tower of london"), Duration(2, "second"))
    val res3 = Await.result(RestClient.execute(index into "london/pubs" fields "name" -> "blue bell"), Duration(2, "second"))
    val res4 = Await.result(RestClient.execute(index into "paris/pubs" fields "name" -> "blue bell"), Duration(2, "second"))
    val res5 = Await.result(RestClient.execute(index into "lyon/pubs" fields "name" -> "blue bell"), Duration(2, "second"))
    val refreshResponse = Await.result(RestClient.refresh(), Duration(2, "second"))
  }

  def initStep2 = {
    val res = Await.result(
      RestClient.bulk(
        index into "waterways/rivers" id 11 fields ("name" -> "River Lune", "country" -> "England"),
        index into "waterways/rivers" id 12 fields ("name" -> "River Dee", "country" -> "England"),
        index into "waterways/rivers2" id 21 fields ("name" -> "River Dee", "country" -> "Wales"),
        index into "waterways_updated/rivers" id 31 fields ("name" -> "Thames", "country" -> "England")
      ), Duration(2, "second")
    )
    val refreshResponse = Await.result(RestClient.refresh(), Duration(2, "second"))
  }
}