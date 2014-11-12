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
  val log = Logger("CountSpec")
  sequential

  def initStep = {
    val res = Await.result(RestClient.execute(index into "london/landmarks" fields "name" -> "hampton court palace"), Duration(1, "second"))
    val res2 = Await.result(RestClient.execute(index into "london/landmarks" fields "name" -> "tower of london"), Duration(1, "second"))
    val res3 = Await.result(RestClient.execute(index into "london/pubs" fields "name" -> "blue bell"), Duration(1, "second"))

  }

  "A count request" should {
    "return total count when no query is specified" in new WithApplication(FakeApplication(additionalPlugins = Seq("playlastik.plugin.PlayLastiKPlugin"), additionalConfiguration = Map("playLastiK.isDevMode" -> true, "playLastiK.cleanOnStop" -> true))) {
      initStep
      val refreshResponse = Await.result(RestClient.refresh("london"), Duration(1, "second"))
      val countResponse = Await.result(RestClient.execute(count from "london"), Duration(1, "second"))
      //log.error(""+countResponse)
      countResponse.count must beEqualTo(3)
      success
    }
  }

  "A count request" should {
    "return total count when no query is specified" in new WithApplication(FakeApplication(additionalPlugins = Seq("playlastik.plugin.PlayLastiKPlugin"), additionalConfiguration = Map("playLastiK.isDevMode" -> true, "playLastiK.cleanOnStop" -> true))) {
      initStep
      val refreshResponse = Await.result(RestClient.refresh("london"), Duration(1, "second"))
      val countResponse = Await.result(RestClient.execute(count from "london" -> "landmarks"), Duration(1, "second"))
      //log.error(""+countResponse)
      countResponse.count must beEqualTo(2)
      success
    }
  }

}