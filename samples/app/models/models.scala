package models

import play.api.libs.json._
import org.joda.time.DateTime

case class Address(number: String, street:String, town : String, zip:String)
case class ScaryDocument(name: String, forName: String, birthDate: DateTime, birthplace: String, address: Address)

object Address{
  implicit val adressformat = Json.format[Address]
}

object ScaryDocument{
  implicit val scarydocumentformat = Json.format[ScaryDocument]
}