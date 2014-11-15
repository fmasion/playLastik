package playlastik.admin

import com.sksamuel.elastic4s.DeleteIndexDefinition
import com.sksamuel.elastic4s.ElasticDsl._
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json.Json
import playlastik.WSimpl
import playlastik.dslHelper.IndicesMgtHelper
import playlastik.models._

import scala.concurrent.Future

//TODO handle ESFailure
// {
//  error: "IndexMissingException[[twitter] missing]",
//  status: 404
// }
trait IndicesMgt {
  this: WSimpl =>

  def refresh(indexes: String*): Future[RefreshIndicesResponse] = {
    val reqInfo = IndicesMgtHelper.getRefreshRequestInfo(serviceUrl, indexes:_*)
    val wsResp = doCall(reqInfo)
    wsResp.map(r => Json.parse(r.body)).map{j =>
      //log.error("RESPONSE" + j)
      j.as[RefreshIndicesResponse]
    }
  }

  def flush(indexes: String*): Future[FlushIndicesResponse] = {
    val reqInfo = IndicesMgtHelper.getFlushRequestInfo(serviceUrl, indexes:_*)
    val wsResp = doCall(reqInfo)
    wsResp.map(r => Json.parse(r.body)).map{j =>
      //log.error("RESPONSE" + j)
      j.as[FlushIndicesResponse]
    }
  }

  def exists(indexes: String*): Future[ExistIndicesResponse] = {
    val reqInfo = IndicesMgtHelper.getExistRequestInfo(serviceUrl, indexes:_*)
    val wsResp = doCall(reqInfo)
    wsResp.map { r =>
      r.status match {
        case 200 => ExistIndicesResponse(true)
        case 404 => ExistIndicesResponse(false)
        case _ =>   ExistIndicesResponse(false)
      }
    }
  }

  def execute(create: CreateIndexDefinition): Future[CreateIndexResponse] = {
    val reqInfo = IndicesMgtHelper.getCreateRequestInfo(serviceUrl,create)
    val wsResp = doCall(reqInfo)
    wsResp.map(r => Json.parse(r.body)).map{j =>
      j.as[CreateIndexResponse]
    }
  }

  def execute(delete: DeleteIndexDefinition): Future[DeleteIndexResponse] = {
    val reqInfo = IndicesMgtHelper.getDeleteRequestInfo(serviceUrl,delete)
    val wsResp = doCall(reqInfo)
    wsResp.map(r => Json.parse(r.body)).map{j =>
      j.as[DeleteIndexResponse]
    }
  }


  //  def execute(i: IndexStatusDefinition): Future[IndicesStatusResponse] = injectFuture[IndicesStatusResponse](client.admin.indices.status(i.build, _))


//  def execute(req: DeleteIndexTemplateDefinition): Future[DeleteIndexTemplateResponse] = {injectFuture[DeleteIndexTemplateResponse](client.admin.indices.deleteTemplate(req.build, _))}
//  def execute(req: CreateIndexTemplateDefinition): Future[PutIndexTemplateResponse] = {injectFuture[PutIndexTemplateResponse](client.admin.indices.putTemplate(req.build, _))}



//  def open(index: String): Future[OpenIndexResponse] = injectFuture[OpenIndexResponse](client.admin.indices.prepareOpen(index).execute)
//  def execute(get: GetMappingDefinition): Future[GetMappingsResponse] = {injectFuture[GetMappingsResponse](client.admin().indices().prepareGetMappings(get.indexes: _*).execute)}

//  def execute(opt: OptimizeRequest): Future[OptimizeResponse] = {injectFuture[OptimizeResponse](client.admin.indices.optimize(opt, _))}
//  def execute(opt: OptimizeDefinition): Future[OptimizeResponse] = execute(opt.build)


//  def execute(put: PutMappingDefinition): Future[PutMappingResponse] = {injectFuture[PutMappingResponse](client.admin.indices.putMapping(put.build, _))}
//  def execute(v: ValidateDefinition): Future[ValidateQueryResponse] = {injectFuture[ValidateQueryResponse](client.admin.indices.validateQuery(v.build, _))}

}
