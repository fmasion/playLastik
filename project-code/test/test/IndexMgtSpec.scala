package test



import org.junit.runner._
import org.specs2.mutable._
import org.specs2.runner._
import play.api.Logger
import play.api.test.{WithApplication, _}
import playlastik._

import scala.concurrent.Await
import scala.concurrent.duration._

@RunWith(classOf[JUnitRunner])
class IndexMgtSpec extends Specification with PlaySpecification {
  val log = Logger("test.IndexMgtSpec")
  sequential

  "An index " should {
    "should be created, checked and deleted" in new WithApplication(FakeApplication(additionalPlugins = Seq("playlastik.plugin.PlayLastiKPlugin"), additionalConfiguration = Map("playLastiK.isDevMode" -> true, "playLastiK.cleanOnStop" -> true))) {
      IndexMgtSpec.createIndexResponse.acknowledged mustEqual(true)
      val existResp = Await.result(RestClient.exists("places"), Duration(2, "second"))
      existResp.exists mustEqual(true)

      IndexMgtSpec.deleteResp.acknowledged mustEqual(true)
      val existResp2 = Await.result(RestClient.exists("places"), Duration(2, "second"))
      existResp2.exists mustEqual(false)
    }
  }
}


object IndexMgtSpec {

  import com.sksamuel.elastic4s.{PatternAnalyzerDefinition, SimpleAnalyzer, KeywordAnalyzer, ElasticDsl}
  import com.sksamuel.elastic4s.mappings.FieldType._
  import ElasticDsl._

  def createIndexResponse = Await.result(RestClient.execute{
    create index "places" shards 3 replicas 2 refreshInterval "10s" mappings(
      "city" as (
        "year_founded" typed IntegerType,
        "location" typed GeoPointType,
        "demonym" typed StringType nullValue "citizen" analyzer KeywordAnalyzer
        ),
      "country" as (
        "name" typed StringType analyzer SimpleAnalyzer
        ) dateDetection true dynamicDateFormats("dd/MM/yyyy", "dd-MM-yyyy")
      ) analysis (
      PatternAnalyzerDefinition("country_code_analyzer", regex = ",")
      )
  }, Duration(2, "second"))

  def deleteResp = Await.result(RestClient.execute(delete index "places"), Duration(2, "second"))

}