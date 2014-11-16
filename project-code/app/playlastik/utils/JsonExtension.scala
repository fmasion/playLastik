package playlastik.utils

/**
 * Created by fred on 16/11/14.
 */
object JsonExtension {
  import play.api.libs.json._

  def withDefault[A](key: String, default: =>A)(implicit writes: Writes[A]) = __.json.update((__ \ key).json.copyFrom((__ \ key).json.pick.filterNot(_ == JsNull) orElse Reads.pure(Json.toJson(default))))

}
