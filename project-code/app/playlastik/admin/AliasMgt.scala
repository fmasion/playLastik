package playlastik.admin

import com.sksamuel.elastic4s.BulkCompatibleDefinition
import playlastik.WSimpl
import playlastik.dslHelper.BulkHelper


trait AliasMgt {
  this: WSimpl =>


//  def execute(req: GetAliasesRequest): Future[GetAliasesResponse] = injectFuture[GetAliasesResponse](client.admin.indices.getAliases(req, _))
//  def execute(req: GetAliasDefinition): Future[GetAliasesResponse] = execute(req.build)
//
//  def execute(req: IndicesAliasesRequest): Future[IndicesAliasesResponse] = injectFuture[IndicesAliasesResponse](client.admin.indices.aliases(req, _))
//  def execute(req: MutateAliasDefinition): Future[IndicesAliasesResponse] = execute(req.build)
//  def execute(req: IndicesAliasesRequestDefinition): Future[IndicesAliasesResponse] = execute(req.build)


}
