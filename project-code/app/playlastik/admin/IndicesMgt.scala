package playlastik.admin

import playlastik.WSimpl


trait IndicesMgt {
  this: WSimpl =>


//  def execute(c: CreateIndexDefinition): Future[CreateIndexResponse] = injectFuture[CreateIndexResponse](client.admin.indices.create(c.build, _))
//  def execute(req: CreateIndexRequest): Future[CreateIndexResponse] = injectFuture[CreateIndexResponse](client.admin.indices.create(req, _))
//  def execute(i: IndexStatusDefinition): Future[IndicesStatusResponse] = injectFuture[IndicesStatusResponse](client.admin.indices.status(i.build, _))

//  def exists(indexes: String*): Future[IndicesExistsResponse] = injectFuture[IndicesExistsResponse](client.admin.indices.prepareExists(indexes: _*).execute)
//  def execute(delete: DeleteIndexDefinition): Future[DeleteIndexResponse] = {injectFuture[DeleteIndexResponse](client.admin.indices.delete(delete.build, _))}
//  def execute(req: DeleteIndexTemplateDefinition): Future[DeleteIndexTemplateResponse] = {injectFuture[DeleteIndexTemplateResponse](client.admin.indices.deleteTemplate(req.build, _))}
//  def execute(req: CreateIndexTemplateDefinition): Future[PutIndexTemplateResponse] = {injectFuture[PutIndexTemplateResponse](client.admin.indices.putTemplate(req.build, _))}

//  def flush(indexes: String*): Future[FlushResponse] = injectFuture[FlushResponse](client.admin.indices.prepareFlush(indexes: _*).execute)
//  def refresh(indexes: String*): Future[RefreshResponse] = injectFuture[RefreshResponse](client.admin.indices.prepareRefresh(indexes: _*).execute)
//  def open(index: String): Future[OpenIndexResponse] = injectFuture[OpenIndexResponse](client.admin.indices.prepareOpen(index).execute)
//  def execute(get: GetMappingDefinition): Future[GetMappingsResponse] = {injectFuture[GetMappingsResponse](client.admin().indices().prepareGetMappings(get.indexes: _*).execute)}


}
