package playlastik.models

import play.api.libs.json.{Reads, Json, JsObject}


case class GetResponse(docs: List[GetResponseItem])
case class GetResponseItem(_index: String, _type: String, _id: String, found: Boolean, _version:Option[Long], _source: Option[JsObject]){
 def asOpt[T](implicit r:Reads[T]) = _source.flatMap(_.asOpt[T](r:Reads[T]))
}


object GetResponseItem {
 implicit val getResponseItemFormat = Json.format[GetResponseItem]
}

object GetResponse {
 implicit val getResponseFormat = Json.format[GetResponse]
}


