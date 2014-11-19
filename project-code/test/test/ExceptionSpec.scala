package test

import com.sksamuel.elastic4s.ElasticDsl._
import org.elasticsearch.index.query.FilterBuilders
import org.junit.runner._
import org.specs2.mutable._
import org.specs2.runner._
import play.api.Logger
import play.api.test.{WithApplication, _}
import playlastik._
import playlastik.elasticSearchException._

import scala.concurrent.Await
import scala.concurrent.duration._

@RunWith(classOf[JUnitRunner])
class ExceptionSpec extends Specification with PlaySpecification {
  val log = Logger("AliasSpec")
  sequential

  def aliasResponse = Await.result(RestClient.execute {add alias "aquatic_locations" on "waterways"}, Duration(2, "second"))

  "An Elastic Search Error" should {
    "be handled as an Furure.Failure of the right Exception" in new WithApplication(FakeApplication(additionalPlugins = Seq("playlastik.plugin.PlayLastiKPlugin"), additionalConfiguration = Map("playLastiK.isDevMode" -> true, "playLastiK.cleanOnStop" -> true))) {
      aliasResponse  must throwA[IndexMissingException]
    }
  }

  "An Elastic Search Error" should {
    "be handled as an Furure.Failure of the Parent Exception" in new WithApplication(FakeApplication(additionalPlugins = Seq("playlastik.plugin.PlayLastiKPlugin"), additionalConfiguration = Map("playLastiK.isDevMode" -> true, "playLastiK.cleanOnStop" -> true))) {
      aliasResponse  must throwA[ElasticSearchException]
    }
  }

  "An Elastic Search Error" should {
    "be not handled as an Furure.Failure of a bad exception" in new WithApplication(FakeApplication(additionalPlugins = Seq("playlastik.plugin.PlayLastiKPlugin"), additionalConfiguration = Map("playLastiK.isDevMode" -> true, "playLastiK.cleanOnStop" -> true))) {
      aliasResponse  must not (throwA[RoutingMissingException])
    }
  }


}

