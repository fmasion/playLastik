package playlastik.models

import play.api.libs.json._

sealed trait ESResponse 
case class ESFailure(error:String, status:Int) extends ESResponse

case class IndexResponse(_index:String, _type:String, _id:String, _version:Long, created:Boolean) extends ESResponse



object IndexResponse{
  implicit val indexResponseFormat = Json.format[IndexResponse]
}

object ESFailure{
  implicit val esFailureFormat = Json.format[ESFailure]
}