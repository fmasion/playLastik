package org.elasticsearch.action.deletebyquery

object DeleteByQueryImplicits {

  implicit class DeleteByQueryRequestOps(val that: DeleteByQueryRequest) extends AnyVal {
    //import scala.collection.JavaConversions._
    def typeList: List[String] = that.types.toList
    def queryString: String = that.source().toUtf8()

  }
}
