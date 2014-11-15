package playlastik

import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.ws.{WS, WSAuthScheme, WSResponse}
import play.api.{Logger, Play}
import playlastik.dslHelper.RequestInfo
import playlastik.method._

import scala.concurrent.Future

/**
 * Created by fred on 05/11/14.
 */
trait WSimpl {
  implicit val app = play.api.Play.current

  val authentificationName = Play.configuration.getString("playLastiK.authentication.scheme").getOrElse("NONE")
  val user = Play.configuration.getString("playLastiK.authentication.user").getOrElse("")
  val pass = Play.configuration.getString("playLastiK.authentication.pass").getOrElse("")
  val serviceUrl = Play.configuration.getString("playLastiK.url").getOrElse("http://localhost:9200")

  def log: Logger

  def getAuthentificationModel(modelName: String) = {
    modelName match {
      case "BASIC" => WSAuthScheme.BASIC
      case "DIGEST" => WSAuthScheme.DIGEST
      case "KERBEROS" => WSAuthScheme.KERBEROS
      case "NTLM" => WSAuthScheme.NTLM
      case "SPNEGO" => WSAuthScheme.SPNEGO
      case _ => WSAuthScheme.NONE
    }
  }

  def doCall(reqInfo: RequestInfo): Future[WSResponse] = {
    //log.error(s"verb : ${reqInfo.method} \nurl : ${reqInfo.url} \nbody : ${reqInfo.body} \nparams : ${reqInfo.queryParams}")
    val rh = if (authentificationName.equalsIgnoreCase("NONE")) {
      WS.url(reqInfo.url)(app).withQueryString(reqInfo.queryParams: _*)
    } else {
      WS.url(reqInfo.url)(app).withQueryString(reqInfo.queryParams: _*).withAuth(user, pass, getAuthentificationModel(authentificationName))
    }
    val fresp = reqInfo.method match {
      case Get => rh.withBody(reqInfo.body).get()
      case Post => rh.post(reqInfo.body)
      case Put => rh.put(reqInfo.body)
      case Delete => rh.withBody(reqInfo.body).delete()
      case Head => rh.withBody(reqInfo.body).head()
    }
    fresp onSuccess {
      case resp => {
        log.debug(s"return code : ${resp.status}")
        if (resp.status >= 500) {
          log.error("Status : " + resp.status + " " + resp.body)
        }
      }
    }
    fresp onFailure {
      case t => log.error(t.getMessage())
    }
    fresp

  }

}
