package com.sksamuel.elastic4s

import com.sksamuel.elastic4s.source.DocumentSource
import play.api.libs.json._

class PlayJsonSource[T](clazz: T)(implicit write: Writes[T]) extends DocumentSource {
  def json = Json.toJson(clazz).toString
}

object PlayJsonSource {
  def apply[T](clazz: T)(implicit write: Writes[T]) = new PlayJsonSource(clazz)

}
