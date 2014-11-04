package playlastik

import com.sksamuel.elastic4s.ElasticDsl._
import com.sksamuel.elastic4s._
import play.api.{Logger, _}
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json._
import play.api.libs.ws.{WSAuthScheme, WSResponse, WS}
import playlastik.dslHelper._
import playlastik.models.{IndexSuccess, StatsResponse}

import scala.concurrent.Future

object RestClient {
  implicit val app = play.api.Play.current

  val log = Logger("playlastik.RestClient")
  val serviceUrl = Play.configuration.getString("playLastiK.url").getOrElse("http://localhost:9200")
  val authentificationName = Play.configuration.getString("playLastiK.authentication.scheme").getOrElse("NONE")
  val user = Play.configuration.getString("playLastiK.authentication.user").getOrElse("")
  val pass = Play.configuration.getString("playLastiK.authentication.pass").getOrElse("")

  def execute(req: SearchDefinition) = search(req)
  def execute(req: IndexDefinition) = index(req)
  def execute(req: GetDefinition) = get(req)


  //    def deleteIndex(deleteIndex: DeleteIndexDefinition)(implicit duration: Duration): DeleteIndexResponse =
  //      ??? //Await.result(client.deleteIndex(deleteIndex), duration)
  //
  //    def percolate(percolateDef: PercolateDefinition)(implicit duration: Duration): PercolateResponse =
  //      ??? //Await.result(client.percolate(percolateDef), duration)
  //
  //    def register(registerDef: RegisterDefinition)(implicit duration: Duration): IndexResponse =
  //      ??? //Await.result(client.register(registerDef), duration)
  //    def optimize(o: OptimizeDefinition)(implicit duration: Duration): OptimizeResponse =
  //      ??? //Await.result(client.optimize(o), duration)
  //
  //    def exists(indexes: String*): IndicesExistsResponse = ??? //Await.result(client.exists(indexes: _*), duration)

  def bulk(reqs: BulkCompatibleDefinition*) = {
    val reqInfo = BulkHelper.getRequestInfo(serviceUrl, reqs: _*)
    doCall(reqInfo)
  }

  def getSource(req: GetDefinition) = get(req=req, source=true)

  def get(req: GetDefinition, source: Boolean) = {
    val reqInfo = GetHelper.getRequestInfo(serviceUrl, req, source)
    doCall(reqInfo)
  }

  def get(gets: GetDefinition*) = {
    val reqInfo = GetHelper.getRequestInfo(serviceUrl, gets)
    doCall(reqInfo)
  }

  // TODO Future[Response]
  def search(req: SearchDefinition): Future[WSResponse] = {
    val reqInfo = SearchHelper.getRequestInfo(serviceUrl, req)
    doCall(reqInfo)
  }

  def index(req: IndexDefinition): Future[IndexSuccess] = {
    val reqInfo = IndexHelper.getRequestInfo(serviceUrl, req)
    val wsResp = doCall(reqInfo)
    wsResp.map(r => Json.parse(r.body)).map(j => (j.as[IndexSuccess]))
  }

  def delete(req: DeleteByIdDefinition) = {
    val reqInfo = DeleteHelper.getRequestInfo(serviceUrl, req)
    doCall(reqInfo)
  }

  def delete(req: DeleteByQueryDefinition) = {
    val reqInfo = DeleteHelper.getRequestInfo(serviceUrl, req)
    doCall(reqInfo)
  }

  def doCall(reqInfo: RequestInfo): Future[WSResponse] = {
    log.debug(s"verb : ${reqInfo.method} \nurl : ${reqInfo.url} \nbody : ${reqInfo.body} \nparams : ${reqInfo.queryParams}")
    val rh = if(authentificationName.equalsIgnoreCase("NONE")){
      WS.url(reqInfo.url)(app).withQueryString(reqInfo.queryParams: _*)
    }else{
      WS.url(reqInfo.url)(app).withQueryString(reqInfo.queryParams: _*).withAuth(user, pass, getAuthentificationModel(authentificationName))
    }
    val fresp = reqInfo.method match {
      case Get => rh.withBody(reqInfo.body).get()
      case Post => rh.post(reqInfo.body)
      case Put => rh.put(reqInfo.body)
      case Delete => rh.withBody(reqInfo.body).delete()
    }
    fresp onSuccess {
      case resp => {
        log.debug(s"return code : ${resp.status}")
        if (resp.status >= 400) {
          log.error("Status : " + resp.status + " " +  resp.body)
        }
      }
    }
    fresp onFailure {
      case t => log.error(t.getMessage())
    }
    fresp

  }
  
  def getAuthentificationModel(modelName: String) ={
    modelName match{
      case "BASIC" => WSAuthScheme.BASIC
      case "DIGEST" => WSAuthScheme.DIGEST
      case "KERBEROS" => WSAuthScheme.KERBEROS
      case "NTLM" => WSAuthScheme.NTLM
      case "SPNEGO" => WSAuthScheme.SPNEGO
      case _  => WSAuthScheme.NONE
    }
  }

  object Admin {
    def stats: Future[StatsResponse] = {
      doCall(RequestInfo(Get, (serviceUrl + "/_stats"), "")).map { r =>
        val jbody = Json.parse(r.body)
        jbody.as[StatsResponse]
      }
    }
  }

}
