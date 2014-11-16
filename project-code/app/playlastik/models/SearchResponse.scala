package playlastik.models

import org.elasticsearch.rest.RestStatus
import play.api.libs.json._
import playlastik.utils.JsonExtension._

case class SearchHits(_index:String, _type:String, _id:String, _score:Double, _source: JsObject)
case class SearchInternalHits(total:Long, max_score:Double, hits: List[SearchHits])
case class SearchFacets(coucou: Option[String]) // facets;
case class SearchAggregations(coucou: Option[String])// aggregations;
case class SearchSuggest(coucou: Option[String]) // suggest;

case class SearchResponse(took:Long,timed_out:Boolean, _shards: ShardStatus, hits: SearchInternalHits,facets: Option[SearchFacets],aggregations:Option[SearchAggregations], suggest: Option[SearchSuggest], terminatedEarly:Option[Boolean] =Some(false)){
  def getHitsAs[A](implicit j: Reads[A]) = {
    val optsA = hits.hits.map(searchHit => searchHit._source.asOpt[A])
    optsA.flatten
  }
}

object SearchHits {
  implicit val searchHitsFormat = Json.format[SearchHits]
}

object SearchInternalHits {

  implicit val searchInternalHitsFormat = new Format[SearchInternalHits] {
    val base = Json.format[SearchInternalHits]
    def reads(json: JsValue): JsResult[SearchInternalHits] = base.compose(withDefault("max_score", 0d)).reads(json)
    def writes(o: SearchInternalHits): JsValue = base.writes(o)
  }
}
object SearchFacets {
  implicit val searchFacetsFormat = Json.format[SearchFacets]
}

object SearchAggregations {
  implicit val searchAggregationsFormat = Json.format[SearchAggregations]
}

object SearchSuggest {
  implicit val searchSuggestFormat = Json.format[SearchSuggest]
}

object SearchResponse {
  implicit val searchResponseFormat = Json.format[SearchResponse]
}
