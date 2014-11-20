package test

import com.sksamuel.elastic4s.ElasticDsl._
import com.sksamuel.elastic4s.PlayJsonSource
import org.elasticsearch.index.query.FilterBuilders
import org.junit.runner._
import org.specs2.mutable._
import org.specs2.runner._
import play.api.Logger
import play.api.libs.json.Json
import play.api.test.{WithApplication, _}
import playlastik._

import scala.concurrent.Await
import scala.concurrent.duration._

case class TestClass(name : String, country : String)

object TestClass{
 implicit val testClassFormat = Json.format[TestClass]
}

@RunWith(classOf[JUnitRunner])
class GetSpec extends Specification with PlaySpecification {
  val log = Logger("test.GetSpec")
  sequential

  val t1 = TestClass("River Lune" , "England" )
  val t2 = TestClass("River Dee" , "England" )

  def initStep = {
    val res = Await.result(
      RestClient.bulk(
        index into "waterways2/rivers" id 11 doc PlayJsonSource(t1),
        index into "waterways2/rivers" id 12 doc PlayJsonSource(t2)
      ), Duration(2, "second")
    )
    val refreshResponse = Await.result(RestClient.refresh(), Duration(2, "second"))
  }

  "A get request" should {
    "return an existing Element" in new WithApplication(FakeApplication(additionalPlugins = Seq("playlastik.plugin.PlayLastiKPlugin"), additionalConfiguration = Map("playLastiK.isDevMode" -> true, "playLastiK.cleanOnStop" -> true))) {
      initStep

      val getResponse = Await.result(RestClient.execute {get id 11 from "waterways2" -> "rivers"}, Duration(2, "second"))
      getResponse.found mustEqual(true)
      getResponse.asOpt[TestClass].get mustEqual(t1)

    }
  }

  "A get request" should {
    "return an empty getResponse if no matching Id" in new WithApplication(FakeApplication(additionalPlugins = Seq("playlastik.plugin.PlayLastiKPlugin"), additionalConfiguration = Map("playLastiK.isDevMode" -> true, "playLastiK.cleanOnStop" -> true))) {
      initStep

      val getResponse = Await.result(RestClient.execute {get id 1234 from "waterways2" -> "rivers"}, Duration(2, "second"))
      getResponse.found mustEqual(false)
      getResponse._source mustEqual(None)

    }
  }

  "A multi get request" should {
    "return all requested Elements" in new WithApplication(FakeApplication(additionalPlugins = Seq("playlastik.plugin.PlayLastiKPlugin"), additionalConfiguration = Map("playLastiK.isDevMode" -> true, "playLastiK.cleanOnStop" -> true))) {
      initStep

      val getResponse = Await.result(RestClient.execute {
        multiget(
          get id 11 from "waterways2" -> "rivers",
          get id 12 from "waterways2" -> "rivers")
        }, Duration(2, "second"))
      getResponse.docs.size mustEqual(2)
      getResponse.docs.head.found mustEqual(true)
      getResponse.docs.head.asOpt[TestClass].get mustEqual(t1)

    }
  }

  "A multi get request" should {
    "return only matching Elements" in new WithApplication(FakeApplication(additionalPlugins = Seq("playlastik.plugin.PlayLastiKPlugin"), additionalConfiguration = Map("playLastiK.isDevMode" -> true, "playLastiK.cleanOnStop" -> true))) {
      initStep

      val getResponse = Await.result(RestClient.execute {
        multiget(
          get id 11 from "waterways2" -> "rivers",
          get id 1222222222 from "waterways2" -> "rivers")
      }, Duration(2, "second"))
      getResponse.docs.size mustEqual(2)
      getResponse.docs.head.found mustEqual(true)
      getResponse.docs.tail.head.found mustEqual(false)
      getResponse.docs.head.asOpt[TestClass].get mustEqual(t1)

    }
  }


}

