package playlastik.admin

import playlastik.WSimpl


trait ClusterMgt {
  this: WSimpl =>

// CLUSTER
//  def shutdown: Future[NodesShutdownResponse] = shutdown("_local")
//  def shutdown(nodeIds: String*): Future[NodesShutdownResponse] = {injectFuture[NodesShutdownResponse](java.admin.cluster.prepareNodesShutdown(nodeIds: _*).execute)}


//  def execute(req: CreateRepositoryDefinition): Future[PutRepositoryResponse] = {injectFuture[PutRepositoryResponse](client.admin.cluster.putRepository(req.build, _))}
//  def execute(c: ClusterHealthDefinition): Future[ClusterHealthResponse] = {injectFuture[ClusterHealthResponse](client.admin.cluster.health(c.build, _))}

// SNAPSHOT
//  def execute(req: CreateSnapshotDefinition): Future[CreateSnapshotResponse] = {injectFuture[CreateSnapshotResponse](client.admin.cluster.createSnapshot(req.build, _))}
//  def execute(req: RestoreSnapshotDefinition): Future[RestoreSnapshotResponse] = {injectFuture[RestoreSnapshotResponse](client.admin.cluster.restoreSnapshot(req.build, _))}
//  def execute(req: DeleteSnapshotDefinition): Future[DeleteSnapshotResponse] = {injectFuture[DeleteSnapshotResponse](client.admin.cluster.deleteSnapshot(req.build, _))}

}
