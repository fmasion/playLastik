package playlastik.dslHelper

import com.sksamuel.elastic4s.{GetAliasDefinition, MutateAliasDefinition}
import com.sksamuel.elastic4s.ElasticDsl._
import play.api.Logger
import play.api.libs.json._
import playlastik.method.{Delete, Get, Method, Post}

object AliasMgtHelper {
  val log = Logger("playlastik.dslHelper.AliasMgtHelper")

  def getMutateRequestInfo(serviceUrl: String, req: MutateAliasDefinition): RequestInfo = {
    val url = serviceUrl + "/_aliases"
    val method: Method = Post

    val oFilter = Option(req.aliasAction.filter()).filterNot(_.isEmpty).map("filter" -> Json.parse(_))
    val oRouting = Option(req.aliasAction.searchRouting()).filterNot(_.isEmpty).map("routing" -> JsString(_))
    val oAlias = Option(req.aliasAction.alias()).map("alias" -> JsString(_))
    val oIndex = Option(req.aliasAction.index()).map("index" -> JsString(_))
    val allOption:List[(String, JsValue)] = List(oFilter, oRouting, oAlias, oIndex).collect{case Some(a) => a}

    val mapInfos = Map[String, JsValue]() ++ allOption

    val doc = Json.obj("actions" -> List(Json.obj(req.aliasAction.actionType().toString.toLowerCase -> mapInfos)))

    RequestInfo(method, url, doc.toString(), Nil)
  }

  def getAliasesInfoRequestInfo(serviceUrl: String, req: GetAliasDefinition): RequestInfo = {
    val method: Method = Get
    val indices = req.request.indices().toList
    val indicesUrlFragment = indices match{
      case Nil => "" // all indices
      case _   => "/" + indices.mkString(",")
    }
    val aliases = req.request.aliases().toList
    val aliasesUrlFragment = aliases match{
      case Nil => ""
      case _   => "/" + aliases.mkString(",")
    }
    val url = serviceUrl + indicesUrlFragment + "/_aliases" + aliasesUrlFragment
    log.error("WTF" + url )
    RequestInfo(method, url, "", Nil)
  }

//  def getDeleteRequestInfo(serviceUrl: String, req: DeleteIndexDefinition): RequestInfo = {
//    val indices = req.build.indices().toList
//    val indicesStr = indices match {
//      case Nil => "/_all/"
//      case _ => "/" + indices.mkString(",") + "/"
//    }
//    val url = serviceUrl + indicesStr
//    val method: Method = Delete
//    RequestInfo(method, url, "", Nil)
//  }


}