package playlastik

import java.util.concurrent.TimeUnit

import org.elasticsearch.ElasticsearchException
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json.{JsValue, JsError, JsSuccess, Json}
import play.api.libs.ws.{WS, WSAuthScheme, WSResponse}
import play.api.{Logger, Play}
import playlastik.dslHelper.RequestInfo
import playlastik.method._
import playlastik.models.ESFailure
import scala.util.{Try}
import scala.concurrent.duration._

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
  val withRetry = Play.configuration.getBoolean("playLastiK.withRetry").getOrElse(true)
  val maxRetry = Play.configuration.getInt("playLastiK.maxRetry").getOrElse(5)
  val delay = Play.configuration.getLong("playLastiK.delay").getOrElse(20L)

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

  def doCall(reqInfo: RequestInfo): Future[JsValue] = {
    //log.error(s"verb : ${reqInfo.method} \nurl : ${reqInfo.url} \nbody : ${reqInfo.body} \nparams : ${reqInfo.queryParams}")
    val rh = if (authentificationName.equalsIgnoreCase("NONE")) {
      WS.url(reqInfo.url)(app).withQueryString(reqInfo.queryParams: _*)
    } else {
      WS.url(reqInfo.url)(app).withQueryString(reqInfo.queryParams: _*).withAuth(user, pass, getAuthentificationModel(authentificationName))
    }

    implicit val success = retry.Success[WSResponse](_ => true)
    import odelay.Timer
    val timer = implicitly[Timer]
    val policy = retry.Backoff(max = maxRetry, delay = Duration(delay, TimeUnit.MILLISECONDS))
    val fresp = policy{ () => reqInfo.method match {
        case Get => rh.withBody(reqInfo.body).get()
        case Post => rh.post(reqInfo.body)
        case Put => rh.put(reqInfo.body)
        case Delete => rh.withBody(reqInfo.body).delete()
        case Head => rh.withBody(reqInfo.body).head()
      }
    }

    val fresp2 = fresp.map{ resp =>
      val j = Try(Json.parse(resp.body)).getOrElse(Json.obj("status" -> resp.status))
      j.asOpt[ESFailure] match {
        case Some(failure) => makeException(failure)
        case None  => j
      }
    }

    fresp2 onFailure {
      case t => log.error(t.getMessage())
    }
    fresp2

  }


  def makeException(failure: ESFailure):JsValue = {
    throw new ElasticsearchException(failure.error)
  }

}
