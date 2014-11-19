package test

import com.sksamuel.elastic4s.ElasticDsl._
import com.sksamuel.elastic4s.PlayJsonSource
import org.junit.runner._
import org.specs2.mutable._
import org.specs2.runner._
import play.api.Logger
import play.api.test.{WithApplication, _}
import playlastik._

import scala.concurrent.Await
import scala.concurrent.duration._


@RunWith(classOf[JUnitRunner])
class DeleteSpec extends Specification with PlaySpecification {
  val log = Logger("test.DeleteSpec")
  sequential

  val t1 = TestClass("River Lune" , "England" )
  val t2 = TestClass("River Dee" , "England" )

  def initStep = {
    val res = Await.result(
      RestClient.bulk(
        index into "delete/rivers" id 11 doc PlayJsonSource(t1),
        index into "delete/rivers" id 12 doc PlayJsonSource(t2)
      ), Duration(2, "second")
    )

    val refreshResponse = Await.result(RestClient.refresh(), Duration(2, "second"))
  }

  def deleteStep = {
    val res = Await.result(
      RestClient.execute(
        delete id 12 from "delete/rivers"
      ), Duration(2, "second")
    )

    val refreshResponse = Await.result(RestClient.refresh(), Duration(2, "second"))
  }

  def deleteStep2 = {
    val res = Await.result(
      RestClient.execute(
        delete id 12222222 from "delete/rivers"
      ), Duration(2, "second")
    )
    //log.error(""+res)

    val refreshResponse = Await.result(RestClient.refresh(), Duration(2, "second"))
  }

  "A delete request" should {
    "return right count" in new WithApplication(FakeApplication(additionalPlugins = Seq("playlastik.plugin.PlayLastiKPlugin"), additionalConfiguration = Map("playLastiK.isDevMode" -> true, "playLastiK.cleanOnStop" -> true))) {
      initStep

      val countResponse = Await.result(RestClient.execute {count from "delete"}, Duration(2, "second"))
      countResponse.count mustEqual(2)

      deleteStep

      val countResponse2 = Await.result(RestClient.execute {count from "delete"}, Duration(2, "second"))
      countResponse2.count mustEqual(1)

    }
  }

  "A delete request with bad id" should {
    "return right count" in new WithApplication(FakeApplication(additionalPlugins = Seq("playlastik.plugin.PlayLastiKPlugin"), additionalConfiguration = Map("playLastiK.isDevMode" -> true, "playLastiK.cleanOnStop" -> true))) {
      initStep

      val countResponse = Await.result(RestClient.execute {count from "delete"}, Duration(2, "second"))
      countResponse.count mustEqual(2)

      deleteStep2

      val countResponse2 = Await.result(RestClient.execute {count from "delete"}, Duration(2, "second"))
      countResponse2.count mustEqual(2)

    }
  }

}

