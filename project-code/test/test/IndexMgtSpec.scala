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
  val log = Logger("IndexMgtSpec")
  sequential

  "A index creation" should {
    "return an acknolegement" in new WithApplication(FakeApplication(additionalPlugins = Seq("playlastik.plugin.PlayLastiKPlugin"), additionalConfiguration = Map("playLastiK.isDevMode" -> true, "playLastiK.cleanOnStop" -> true))) {
      IndexMgtSpec.createIndexResponse.acknowledged mustEqual(true)
    }
  }
}


object IndexMgtSpec {

  import com.sksamuel.elastic4s.{PatternAnalyzerDefinition, SimpleAnalyzer, KeywordAnalyzer, ElasticDsl}
  import com.sksamuel.elastic4s.mappings.FieldType._
  import ElasticDsl._

  val createIndexResponse = Await.result(RestClient.execute{
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
  }, Duration(1, "second"))

}