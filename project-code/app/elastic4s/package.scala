package com.sksamuel

import com.sksamuel.elastic4s.source.DocumentSource
import play.api.libs.json._

package object elastic4s {
      implicit def case2PlayJsonSource[T](clazz:T)(implicit write:Writes[T]):DocumentSource = PlayJsonSource(clazz)
}