package com.sksamuel.elastic4s

import play.api.libs.json._
import com.sksamuel.elastic4s.source.DocumentSource

class PlayJsonSource[T](clazz:T)(implicit write:Writes[T]) extends DocumentSource  {
	def json = Json.toJson(clazz).toString
}

object PlayJsonSource {
  def apply[T](clazz: T)(implicit write:Writes[T]) = new PlayJsonSource(clazz)

}
