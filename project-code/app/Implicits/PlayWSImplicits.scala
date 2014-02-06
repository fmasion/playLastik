package play.api.libs.ws


import scala.concurrent.Future
import java.io.File
import play.api.http.{ Writeable, ContentTypeOf }
import play.api.libs.ws.WS._
import play.api.libs.iteratee.Iteratee

// Implicits are used to extend WSRequestHolder to enable body to be passed with GET and DELETE Methods
// Won't be needed after issue #2015 will be fixed
object Implicits {

  implicit class WSRequestHolderOps(val rh: WSRequestHolder) extends AnyVal {

    /**
     * Perform a GET on the request asynchronously.
     * @param body supplied won't be chunked
     */
    def get[T](body: T)(implicit wrt: Writeable[T], ct: ContentTypeOf[T]): Future[Response] = rh.prepare("GET", body).execute

    /**
     * Perform a GET on the request asynchronously.
     * @param body supplied won't be chunked
     */
    def get(body: File): Future[Response] = rh.prepare("GET", body).execute

    /**
     * performs a get with supplied body
     * @param body won't be chunked
     * @param consumer that's handling the response
     */
    def get[A, T](body: T, consumer: ResponseHeaders => Iteratee[Array[Byte], A])(implicit wrt: Writeable[T], ct: ContentTypeOf[T]): Future[Iteratee[Array[Byte], A]] =
      rh.prepare("GET", body).executeStream(consumer)

    /**
     * Perform a DELETE on the request asynchronously.
     * @param body supplied won't be chunked
     */
    def delete[T](body: T)(implicit wrt: Writeable[T], ct: ContentTypeOf[T]): Future[Response] = rh.prepare("DELETE", body).execute

    /**
     * Perform a DELETE on the request asynchronously.
     * @param body supplied won't be chunked
     */
    def delete(body: File): Future[Response] = rh.prepare("DELETE", body).execute

    /**
     * performs a delete with supplied body
     * @param body won't be chunked
     * @param consumer that's handling the response
     */
    def delete[A, T](body: T, consumer: ResponseHeaders => Iteratee[Array[Byte], A])(implicit wrt: Writeable[T], ct: ContentTypeOf[T]): Future[Iteratee[Array[Byte], A]] =
      rh.prepare("DELETE", body).executeStream(consumer)

  }

}