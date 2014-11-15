package playlastik.models

import org.elasticsearch.rest.RestStatus
import play.api.libs.json._

sealed trait ESResponse

case class ESFailure(error: String, status: Int) extends ESResponse

case class IndexResponse(_index: String, _type: String, _id: String, _version: Long, created: Boolean) extends ESResponse

case class ShardStatus(total:Int, successful:Int, failed:Int)

case class CountResponse(count: Long, _shards: ShardStatus, terminatedEarly:Option[Boolean] =Some(false))


// pattern => "_shards":{"total":5,"successful":5,"failed":0}

// pattern BroadcastOperationResponse

case class RefreshIndicesResponse(_shards :ShardStatus)
case class FlushIndicesResponse(_shards :ShardStatus)


case class ExistIndicesResponse(exists :Boolean)
case class CreateIndexResponse(acknowledged :Boolean)
case class DeleteIndexResponse(acknowledged :Boolean)

object ShardStatus {
  implicit val shardStatusFormat = Json.format[ShardStatus]
}

object CountResponse {
  implicit val countResponseFormat = Json.format[CountResponse]
}

object IndexResponse {
  implicit val indexResponseFormat = Json.format[IndexResponse]
}

object ESFailure {
  implicit val esFailureFormat = Json.format[ESFailure]
}

object RefreshIndicesResponse {
  implicit val refreshIndicesResponseFormat = Json.format[RefreshIndicesResponse]
}

object FlushIndicesResponse {
  implicit val flushIndicesResponseFormat = Json.format[FlushIndicesResponse]
}

object ExistIndicesResponse {
  implicit val existIndicesResponseFormat = Json.format[ExistIndicesResponse]
}

object CreateIndexResponse {
  implicit val createIndexResponseFormat = Json.format[CreateIndexResponse]
}

object DeleteIndexResponse {
  implicit val deleteIndexResponseFormat = Json.format[DeleteIndexResponse]
}

