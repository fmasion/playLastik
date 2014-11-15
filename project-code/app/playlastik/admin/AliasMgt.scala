package playlastik.admin

import com.sksamuel.elastic4s.{MutateAliasDefinition, BulkCompatibleDefinition}
import play.api.libs.json.Json
import playlastik.WSimpl
import playlastik.dslHelper.{AliasMgtHelper, IndicesMgtHelper, BulkHelper}
import playlastik.models.{MutateAliasResponse, DeleteIndexResponse}
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

//  def execute(req: GetAliasesRequest): Future[GetAliasesResponse] = injectFuture[GetAliasesResponse](client.admin.indices.getAliases(req, _))
//  def execute(req: GetAliasDefinition): Future[GetAliasesResponse] = execute(req.build)
//
//  def execute(req: IndicesAliasesRequest): Future[IndicesAliasesResponse] = injectFuture[IndicesAliasesResponse](client.admin.indices.aliases(req, _))
//  def execute(req: MutateAliasDefinition): Future[IndicesAliasesResponse] = execute(req.build)
//  def execute(req: IndicesAliasesRequestDefinition): Future[IndicesAliasesResponse] = execute(req.build)


}
