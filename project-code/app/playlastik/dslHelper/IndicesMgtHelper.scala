package playlastik.dslHelper

import com.sksamuel.elastic4s.DeleteIndexDefinition
import com.sksamuel.elastic4s.ElasticDsl._
import com.sksamuel.elastic4s.Implicits._
import play.api.libs.json._
import playlastik.method.{Method, Post, Head, Delete}

object IndicesMgtHelper {

  def getRefreshRequestInfo(serviceUrl: String, indices: String*): RequestInfo = {
    val indicesStr = indices match {
        case Nil => "/"
        case _ => "/" + indices.mkString(",") + "/"
    }
    val esType = "_refresh"
    val url = serviceUrl + indicesStr + esType
    val method: Method = Post
    RequestInfo(method, url, "", Nil)
  }

  def getFlushRequestInfo(serviceUrl: String, indices: String*): RequestInfo = {
    val indicesStr = indices match {
      case Nil => "/"
      case _ => "/" + indices.mkString(",") + "/"
    }
    val esType = "_flush"
    val url = serviceUrl + indicesStr + esType
    val method: Method = Post
    RequestInfo(method, url, "", Nil)
  }

  def getExistRequestInfo(serviceUrl: String, indices: String*): RequestInfo = {
    val indicesStr = indices match {
      case Nil => "/"
      case _ => "/" + indices.mkString(",") + "/"
    }
    val url = serviceUrl + indicesStr
    val method: Method = Head
    RequestInfo(method, url, "", Nil)
  }


  def getCreateRequestInfo(serviceUrl: String, req: CreateIndexDefinition): RequestInfo = {
    val url = serviceUrl + "/" + req.build.indices().head
    val method: Method = Post
    RequestInfo(method, url, req._source.string, Nil)
  }

  def getDeleteRequestInfo(serviceUrl: String, req: DeleteIndexDefinition): RequestInfo = {
    val indices = req.build.indices().toList
    val indicesStr = indices match {
      case Nil => "/_all/"
      case _ => "/" + indices.mkString(",") + "/"
    }
    val url = serviceUrl + indicesStr
    val method: Method = Delete
    RequestInfo(method, url, "", Nil)
  }


}