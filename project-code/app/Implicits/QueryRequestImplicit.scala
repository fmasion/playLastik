package org.elasticsearch.action.deletebyquery

object QueryRequestImplicit {

  implicit class DeleteByQueryRequestOps(val that: DeleteByQueryRequest) extends AnyVal {
    def typeList: List[String] = that.types().toList
    def queryString: String = that.source().toUtf8()

  }

}