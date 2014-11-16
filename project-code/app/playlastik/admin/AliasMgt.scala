package playlastik.admin

import com.sksamuel.elastic4s.{GetAliasDefinition, MutateAliasDefinition}
import play.api.libs.json.Json
import playlastik.WSimpl
import playlastik.dslHelper.{AliasMgtHelper}
import playlastik.models.{AliasDefinition, MutateAliasResponse}
import play.api.libs.concurrent.Execution.Implicits.defaultContext

import scala.concurrent.Future


trait AliasMgt {
  this: WSimpl =>

    def execute(req: MutateAliasDefinition): Future[MutateAliasResponse] = {
      val reqInfo = AliasMgtHelper.getMutateRequestInfo(serviceUrl,req)
      val wsResp = doCall(reqInfo)
      wsResp.map(r => Json.parse(r.body)).map{j =>
        j.as[MutateAliasResponse]
      }
    }

    def execute(req: GetAliasDefinition): Future[Map[String, AliasDefinition]]= {
      val reqInfo = AliasMgtHelper.getAliasesInfoRequestInfo(serviceUrl,req)
      val wsResp = doCall(reqInfo)
      log.error("" + reqInfo)
      wsResp.map(r => Json.parse(r.body)).map{j =>
        j.as[Map[String, AliasDefinition]]
      }
    }

//  def execute(req: GetAliasesRequest): Future[GetAliasesResponse] = injectFuture[GetAliasesResponse](client.admin.indices.getAliases(req, _))

//
//  def execute(req: IndicesAliasesRequest): Future[IndicesAliasesResponse] = injectFuture[IndicesAliasesResponse](client.admin.indices.aliases(req, _))
//  def execute(req: MutateAliasDefinition): Future[IndicesAliasesResponse] = execute(req.build)
//  def execute(req: IndicesAliasesRequestDefinition): Future[IndicesAliasesResponse] = execute(req.build)


}
