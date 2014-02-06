package playlastik.models
import play.api.libs.json._

case class StatsResponse(ok: Boolean, _shards: Shards, _all: Agregations, indices: Map[String, Agregations]){
  def getIndicesName :List[String] = {
    indices.keys.toList
  }
  
  
}
case class Agregations(primaries: Stat, total: Stat)
case class Shards(total: Double, successful: Double, failed: Double)
case class Stat(docs: Option[Docs], store: Option[Store], indexing: Option[Indexing], get: Option[Get], search: Option[Search])

case class Docs(count: Double, deleted: Double)
case class Store(size: String, size_in_bytes: Double, throttle_time: String, throttle_time_in_millis: Double)
case class Indexing(index_total: Double, index_time: String, index_time_in_millis: Double, index_current: Double, delete_total: Double, delete_time: String, delete_time_in_millis: Double, delete_current: Double)
case class Get(total: Double, get_time: String, time_in_millis: Double, exists_total: Double, exists_time: String, exists_time_in_millis: Double, missing_total: Double, missing_time: String, missing_time_in_millis: Double, current: Double)
case class Search(open_contexts: Double, query_total: Double, query_time: String, query_time_in_millis: Double, query_current: Double, fetch_total: Double, fetch_time: String, fetch_time_in_millis: Double, fetch_current: Double)

object Search {
  implicit val searchformat = Json.format[Search]
}
object Get {
  implicit val getformat = Json.format[Get]
}
object Indexing {
  implicit val indexingformat = Json.format[Indexing]
}
object Store {
  implicit val storeformat = Json.format[Store]
}
object Docs {
  implicit val docsformat = Json.format[Docs]
}
object Stat {
  implicit val statformat = Json.format[Stat]
}
object Shards {
  implicit val shardsformat = Json.format[Shards]
}
object Agregations {
  implicit val agregationformat = Json.format[Agregations]
}
object StatsResponse {
  implicit val statsResponseformat = Json.format[StatsResponse]
}



