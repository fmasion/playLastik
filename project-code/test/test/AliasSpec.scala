package test

import com.sksamuel.elastic4s.ElasticDsl._
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

@RunWith(classOf[JUnitRunner])
class AliasSpec extends Specification with PlaySpecification {
  val log = Logger("AliasSpec")
  sequential

  def initStep = {
    val res = Await.result(
      RestClient.bulk(
        index into "waterways/rivers" id 11 fields ("name" -> "River Lune", "country" -> "England"),
        index into "waterways/rivers" id 12 fields ("name" -> "River Dee", "country" -> "England"),
        index into "waterways/rivers" id 21 fields ("name" -> "River Dee", "country" -> "Wales"),
        index into "waterways/rivers" id 21 fields ("name" -> "River Dee", "country" -> "Wales")
      ), Duration(2, "second")
    )
    val res5 = Await.result(RestClient.execute(index into "waterways_updated/rivers" id 31 fields ("name" -> "Thames", "country" -> "England")), Duration(2, "second"))
    val refreshResponse = Await.result(RestClient.refresh(), Duration(2, "second"))
  }

  "A alias creation request" should {
    "return an acknolegment" in new WithApplication(FakeApplication(additionalPlugins = Seq("playlastik.plugin.PlayLastiKPlugin"), additionalConfiguration = Map("playLastiK.isDevMode" -> true, "playLastiK.cleanOnStop" -> true))) {
      initStep

      val aliasResponse = Await.result(RestClient.execute {add alias "aquatic_locations" on "waterways"}, Duration(2, "second"))
      aliasResponse.acknowledged mustEqual(true)
      val aliasResponse2 = Await.result(RestClient.execute {add alias "english_waterways" on "waterways" filter FilterBuilders.termFilter("country", "england")}, Duration(2, "second"))
      aliasResponse2.acknowledged mustEqual(true)
      val aliasResponse3 = Await.result(RestClient.execute {add alias "moving_alias" on "waterways"}, Duration(2, "second"))
      aliasResponse3.acknowledged mustEqual(true)

    }
  }

  "waterways index" should {
    "return 'River Dee' in England and Wales for search" in new WithApplication(FakeApplication(additionalPlugins = Seq("playlastik.plugin.PlayLastiKPlugin"), additionalConfiguration = Map("playLastiK.isDevMode" -> true, "playLastiK.cleanOnStop" -> true))) {
      initStep

      val aliasResponse = Await.result(RestClient.execute {add alias "aquatic_locations" on "waterways"}, Duration(2, "second"))
      val aliasResponse2 = Await.result(RestClient.execute {add alias "english_waterways" on "waterways" filter FilterBuilders.termFilter("country", "england")}, Duration(2, "second"))
      val aliasResponse3 = Await.result(RestClient.execute {add alias "moving_alias" on "waterways"}, Duration(2, "second"))

      val search = AliasSpec.search1
      search.hits.total === 2
      val hitIds = search.hits.hits.map(hit => hit._id).toList.sorted
      hitIds === List("12", "21")

    }
  }

  "english_waterways alias" should {
    "return 'River Dee' in England for search" in new WithApplication(FakeApplication(additionalPlugins = Seq("playlastik.plugin.PlayLastiKPlugin"), additionalConfiguration = Map("playLastiK.isDevMode" -> true, "playLastiK.cleanOnStop" -> true))) {
      initStep

      val aliasResponse = Await.result(RestClient.execute {add alias "aquatic_locations" on "waterways"}, Duration(2, "second"))
      val aliasResponse2 = Await.result(RestClient.execute {add alias "english_waterways" on "waterways" filter FilterBuilders.termFilter("country", "england")}, Duration(2, "second"))
      val aliasResponse3 = Await.result(RestClient.execute {add alias "moving_alias" on "waterways"}, Duration(2, "second"))

      val search = AliasSpec.search2
      search.hits.total === 1
      val hitIds = search.hits.hits.map(hit => hit._id).toList.sorted
      hitIds === List("12")

    }
  }

  "RestClient alias" should {
    "be in query for alias" in new WithApplication(FakeApplication(additionalPlugins = Seq("playlastik.plugin.PlayLastiKPlugin"), additionalConfiguration = Map("playLastiK.isDevMode" -> true, "playLastiK.cleanOnStop" -> true))) {
      initStep

      val aliasResponse = Await.result(RestClient.execute {add alias "aquatic_locations" on "waterways"}, Duration(2, "second"))
      val aliasResponse2 = Await.result(RestClient.execute {add alias "english_waterways" on "waterways" routing "4" filter FilterBuilders.termFilter("country", "england")}, Duration(2, "second"))
      val aliasResponse3 = Await.result(RestClient.execute {add alias "moving_alias" on "waterways"}, Duration(2, "second"))

      val getAlias = AliasSpec.getAlias
      getAlias.contains("waterways") mustEqual(true)
      getAlias("waterways").aliases("english_waterways").index_routing === Some("4")
      getAlias("waterways").aliases.contains("moving_alias") mustEqual(false)

    }
  }

  "RestClient alias" should {
    "move alias between indices" in new WithApplication(FakeApplication(additionalPlugins = Seq("playlastik.plugin.PlayLastiKPlugin"), additionalConfiguration = Map("playLastiK.isDevMode" -> true, "playLastiK.cleanOnStop" -> true))) {
      initStep

      val aliasResponse = Await.result(RestClient.execute {add alias "aquatic_locations" on "waterways"}, Duration(2, "second"))
      val aliasResponse2 = Await.result(RestClient.execute {add alias "english_waterways" on "waterways" routing "4" filter FilterBuilders.termFilter("country", "england")}, Duration(2, "second"))
      val aliasResponse3 = Await.result(RestClient.execute {add alias "moving_alias" on "waterways"}, Duration(2, "second"))

      val refreshResponse = Await.result(RestClient.refresh(), Duration(2, "second"))

      val existResp = Await.result(RestClient.exists("waterways"), Duration(2, "second"))
      existResp.exists mustEqual(true)

      val searchResponse = Await.result(RestClient.search(search in "*" ), Duration(2, "second"))
      //log.error(""+searchResponse)


      val existResp1 = Await.result(RestClient.exists("waterways_updated"), Duration(2, "second"))
      existResp1.exists mustEqual(true)

      val updateAliases = AliasSpec.updateAliases
      updateAliases.acknowledged === true
      val getAliasUpdated = AliasSpec.getAliasUpdated
      getAliasUpdated.contains("waterways_updated") mustEqual(true)
      getAliasUpdated("waterways_updated").aliases.contains("moving_alias") mustEqual(true)

      val getAlias2 = AliasSpec.getAlias
      getAlias2.contains("waterways") mustEqual(true)
      getAlias2("waterways").aliases.contains("moving_alias") mustEqual(false)
    }
  }





}

object AliasSpec {
  def search1 = Await.result(RestClient.execute(search in "waterways" query "Dee"), Duration(2, "second"))
  def search2 = Await.result(RestClient.execute(search in "english_waterways" query "Dee"), Duration(2, "second"))

  def getAlias = Await.result(RestClient.execute(get alias "english_waterways" on "waterways"), Duration(2, "second"))
  def getAlias2 = Await.result(RestClient.execute(get alias "moving_alias" on "waterways"), Duration(2, "second"))
  def getAliasUpdated = Await.result(RestClient.execute(get alias "moving_alias" on "waterways_updated"), Duration(2, "second"))

  def updateAliases = Await.result(RestClient.execute(  aliases(
    remove alias "moving_alias" on "waterways",
    add alias "moving_alias" on "waterways_updated")
  ), Duration(2, "second"))

}