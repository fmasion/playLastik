package playlastik.dslHelper

import playlastik.method.Method

case class RequestInfo(method: Method, url: String, body: String, queryParams: List[(String, String)] = Nil) {

}