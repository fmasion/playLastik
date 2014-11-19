package playlastik.models

import play.api.libs.json.Json

// {"found":true,"_index":"delete","_type":"rivers","_id":"12","_version":2}
case class DeleteResponse(found: Boolean,_index: String, _type: String, _id: String, _version: Long)


object DeleteResponse {
  implicit val deleteResponseFormat = Json.format[DeleteResponse]
}









