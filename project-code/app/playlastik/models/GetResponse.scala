package playlastik.models

import play.api.libs.json.{Reads, Json, JsObject}


case class MultiGetResponse(docs: List[GetResponse])
case class GetResponse(_index: String, _type: String, _id: String, found: Boolean, _version:Option[Long], _source: Option[JsObject]){
 def asOpt[T](implicit r:Reads[T]) = _source.flatMap(_.asOpt[T](r:Reads[T]))
}


object GetResponse {
 implicit val getResponseItemFormat = Json.format[GetResponse]
}

object MultiGetResponse {
 implicit val multiGetResponseFormat = Json.format[MultiGetResponse]
}


