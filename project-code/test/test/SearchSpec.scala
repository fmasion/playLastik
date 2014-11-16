package test

import com.sksamuel.elastic4s.ElasticDsl._
import org.junit.runner._
import org.specs2.mutable._
import org.specs2.runner._
import play.api.Logger
import play.api.libs.json.{Json, JsObject}
import play.api.test.{WithApplication, _}
import playlastik._

import scala.concurrent.Await
import scala.concurrent.duration._

@RunWith(classOf[JUnitRunner])
class SearchSpec extends Specification with PlaySpecification {
  val log = Logger("SearchSpec")
  sequential

  def initStep = {
    val res = Await.result(
      RestClient.bulk(
          index into "london/landmarks" fields ("name" -> "hampton court palace"),
          index into "london/landmarks" fields ("name" -> "tower of london"),
          index into "london/pubs" fields ("name" -> "blue bell"),
          index into "paris/pubs" fields ("name" -> "blue bell"),
          index into "lyon/pubs" fields ("name" -> "blue bell")
      ), Duration(1, "second")
    )
    val refreshResponse = Await.result(RestClient.refresh(), Duration(2, "second"))
  }

  "A search request" should {
    "return the relevant elements" in new WithApplication(FakeApplication(additionalPlugins = Seq("playlastik.plugin.PlayLastiKPlugin"), additionalConfiguration = Map("playLastiK.isDevMode" -> true, "playLastiK.cleanOnStop" -> true))) {
      initStep

      val searchResponse = Await.result(RestClient.search(search in "london"->"landmarks" query "palace"), Duration(2, "second"))
      searchResponse.hits.total === 1
      searchResponse.hits.hits.head._source === Json.obj("name" -> "hampton court palace")
    }
  }

  "A search request" should {
    "return the relevant elements" in new WithApplication(FakeApplication(additionalPlugins = Seq("playlastik.plugin.PlayLastiKPlugin"), additionalConfiguration = Map("playLastiK.isDevMode" -> true, "playLastiK.cleanOnStop" -> true))) {
      initStep

      val searchResponse = Await.result(RestClient.search(search in "london"->"landmarks" query "palace"), Duration(2, "second"))
      searchResponse.hits.total === 1
      searchResponse.hits.hits.head._source === Json.obj("name" -> "hampton court palace")
    }
  }



}