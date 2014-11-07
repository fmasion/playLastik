package playlastik.dslHelper

import com.sksamuel.elastic4s.ElasticDsl._
import com.sksamuel.elastic4s._
import com.sksamuel.elastic4s.Implicits._
import playlastik.method.Post

object BulkHelper {

  def getRequestInfo(serviceUrl: String, reqs: BulkCompatibleDefinition*): RequestInfo = {

    def formatBulk(bulk: BulkCompatibleDefinition): List[String] = {
      bulk match {
        case index: IndexDefinition => IndexHelper.getBulk(index)
        case delete: DeleteByIdDefinition => DeleteHelper.getBulk(delete)
        case update: UpdateDefinition => UpdateHelper.getBulk(update)
      }
    }
    val body = reqs.toList.flatMap(formatBulk).mkString("\n")

    val url = serviceUrl + "/_bulk"

    //TODO
    // handle parent / operation / routing / version in query parameter
    val lOptQueryParams: List[Option[(String, String)]] = (
      //      req.oParent.map(p => "parent" -> p) ::
      //      req.oVersion.map(v => "version" -> v) ::
      //      req.oOpType.map(t => "op_type" -> t) ::
      //      req.oRouting.map(r => "routing" -> r) ::
      Nil)

    val queryParams = lOptQueryParams flatMap (_.toList)

    RequestInfo(Post, url, body, queryParams)
  }

}