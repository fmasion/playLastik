package test.models

import play.api.libs.json._
import play.api.libs.functional.syntax.functionalCanBuildApplicative
import play.api.libs.functional.syntax.toFunctionalBuilderOps


  case class Phone(name: String, brand: String)
  
object Phone{
  implicit val phoneformat = Json.format[Phone]
  }