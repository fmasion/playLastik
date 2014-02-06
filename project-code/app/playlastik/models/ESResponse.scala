package playlastik.models

import play.api.libs.json._

sealed trait ESResponse 
case class ESFailure(error:String, status:Int) extends ESResponse

case class IndexSuccess(ok:Boolean, _index:String, _type:String, _id:String, _version:Long) extends ESResponse

object IndexSuccess{
  implicit val succesIndexFormat = Json.format[IndexSuccess]
}

object ESFailure{
  implicit val succesIndexFormat = Json.format[ESFailure]
}