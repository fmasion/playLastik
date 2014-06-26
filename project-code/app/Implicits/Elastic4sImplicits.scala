package com.sksamuel.elastic4s

import play.api.libs.json.Writes
//import scala.collection.JavaConversions._

object Implicits {
  
  implicit class SearchDefinitionOps(val that: com.sksamuel.elastic4s.ElasticDsl.SearchDefinition) extends AnyVal {
    def actionName = that.action.name()
    def indices: List[String] = that._builder.request().indices().toList
    def typeList: List[String] = that._builder.request().types().toList
    def queryString: String = that._builder.toString()
    def oRouting: Option[String] = Option(that.build.routing())

  }

  implicit class IndexDefinitionOps(val that: com.sksamuel.elastic4s.ElasticDsl.IndexDefinition) extends AnyVal {
    def queryString: String = that.build.source().toUtf8()
    def oId: Option[String] = Option(that.build.id()).filter(s => !s.isEmpty())
    def oParent: Option[String] = Option(that.build.parent()).filter(s => !s.isEmpty())
    def oVersion: Option[Long] = Option(that.build.version()).filter(s => s != 0L)
    private def tmpVersionType: Option[String] = Option(that.build.versionType().name().toLowerCase()).filter(s => !s.isEmpty())
    def oVersionType: Option[String] = for (v <- oVersion; t <- tmpVersionType) yield (t)
    def oRouting: Option[String] = Option(that.build.routing()).filter(s => !s.isEmpty())
    def oRefresh: Option[Boolean] = Option(that.build.refresh()).filter(b => b)
    def oTtl: Option[Long] = Option(that.build.ttl()).filter(s => s != -1L)
    def oTimeStamp: Option[String] = Option(that.build.timestamp()).filter(s => !s.isEmpty())
    def oOpType: Option[String] = Option(that.build.opType().lowercase()).filter(s => !s.isEmpty())

  }

  implicit class UpdateDefinitionOps(val that: com.sksamuel.elastic4s.ElasticDsl.UpdateDefinition) extends AnyVal {
    def oId: Option[String] = Option(that.build.id()).filter(s => !s.isEmpty())
    def oRouting: Option[String] = Option(that.build.routing()).filter(s => !s.isEmpty())
    def oRefresh: Option[Boolean] = Option(that.build.refresh()).filter(b => b)
    def oFields: Option[String] = if (that.build.fields().isEmpty) None else Some(that.build.fields().mkString(","))
    def oScript: Option[String] = Option(that.build.script()).filter(s => !s.isEmpty())
    def oScriptLang: Option[String] = Option(that.build.scriptLang()).filter(s => !s.isEmpty())
    def oScriptParams: Option[String] = Option(that.build.scriptParams()).filter(s => !s.isEmpty()).map(m => paramToString(m)) //that.build.scriptParams())
    def oRetryOnConflict: Option[Int] = Option(that.build.retryOnConflict()).filter(s => s != 0)
    def oUpsertRequest: Option[String] = Option(that.build.upsertRequest()).map(_.source().toUtf8()).filter(s => !s.isEmpty())


    // TODO Param  
    private def paramToString(map: java.util.Map[String,Object]):String = {
      ""
    }
  }
}