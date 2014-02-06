package playlastik.dslHelper

import playlastik.Method

case class RequestInfo(method:Method, url:String, body:String, queryParams: List[(String,String)] = Nil) {

}