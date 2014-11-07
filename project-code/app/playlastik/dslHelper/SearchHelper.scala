package playlastik.dslHelper

import com.sksamuel.elastic4s._
import com.sksamuel.elastic4s.ElasticDsl._
import com.sksamuel.elastic4s.Implicits._
import playlastik.method.Post


object SearchHelper {
  val action = "_search"

  def getRequestInfo(serviceUrl: String, req: SearchDefinition): RequestInfo = {
    val indices = if (req.indices.isEmpty || req.indices.contains("*")) "/_all" else "/" + req.indices.mkString(",")
    val types = if (req.typeList.isEmpty || req.typeList.contains("*")) "" else "/" + req.typeList.mkString(",")
    val url = serviceUrl + indices + types + "/" + action

    // handle parent / operation / routing / version in query parameter
    val lOptQueryParams: List[Option[(String, String)]] = (
      req.oRouting.map(r => "routing" -> r) ::
        Nil)

    val queryParams = lOptQueryParams flatMap (_.toList)

    RequestInfo(Post, url, req.queryString, queryParams)
  }

}