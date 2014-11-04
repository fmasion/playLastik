package controllers

import play.api.mvc._
import play.api.libs.iteratee.Concurrent
import play.api.libs.json._
import play.api.libs.EventSource
import play.api.libs.oauth.ConsumerKey
import play.api.libs.oauth.RequestToken
import play.api.libs.ws._
import play.api.libs.oauth.OAuthCalculator
import play.api.libs.iteratee._
import models._
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import playlastik.RestClient
import com.sksamuel.elastic4s._
import com.sksamuel.elastic4s.ElasticDsl._
import play.api.Logger
import play.api.Play.current

object Application extends Controller {

  val consumerKey = ConsumerKey("T1VwmDYk0IjgfLpYx8qpg", "yvX9A8YlHirVzzBo87oZ29JbFfFy96aoQaeAd1PFo")
  val accessToken = RequestToken("485841773-pswJ0HSC8bFbChrdsypebPyBt5KWwaVm3rE3joi5", "PsOrkwszkNldRJtfV8Stk9mGm0wVabSaJ3GmcJieKuS4P")
  val url = "https://stream.twitter.com/1.1/statuses/filter.json?" + "track=foot%2Csport%2Cpleasure" //"locations=-6,41,10,51" // + "track=mooc%2Celearning%2Ccourse"

  val log = Logger("app")

  /** system-wide channels / enumerators for attaching SSE streams to clients*/
  val (jsonMediasOut, jsonTweetsChannel) = Concurrent.broadcast[JsValue]

  def home = Action {
    Ok(views.html.index())
  }

  def mediaFeed = Action {
    Ok.chunked(jsonMediasOut &> EventSource()).as("text/event-stream")
  }

  // object MediaStream {

  val tweetIteratee = Iteratee.foreach[Array[Byte]] { chunk =>
    {
      val chunkString = new String(chunk, "UTF-8")

      if (chunkString.contains("Easy there, Turbo. Too many requests recently. Enhance your calm.")) {
        println("\n" + chunkString + "\n")
      }

      log.debug("chuncked JSON " + chunkString)
      val json = Json.parse(chunkString)
      log.debug("JSON " + json)
      val tweet = json.asOpt[Tweet]
      log.debug("TWEET " + tweet)
      tweet.map(indexIt)
      if (!tweet.isDefined){
        println(json.validate[Tweet])
      }

      //println(json)
      //val ou = for ( t <- tweet ; e <- t.entities if (!e.urls.isEmpty)) yield (e.urls)
      //ou.map(us => us.foreach(tu => println(tu.expanded_url)))

      val om = for (t <- tweet; e <- t.entities; lm <- e.media if (!lm.isEmpty)) yield (lm)
      om.map(lm => lm.map(m => { jsonTweetsChannel.push(Json.obj("name" -> "test", "url" -> m.media_url)) }))

    }
  }

  WS.url(url).withRequestTimeout(-1).sign(OAuthCalculator(consumerKey, accessToken)).get(_ => tweetIteratee)

  def searchIt(term:String) = Action.async { implicit request =>
    val fsearch = RestClient.search { search in "monindex" types "testmonType" query term start 0 limit 10 }
    fsearch.map(r => Ok(r.body))
  }

  def indexIt(tweet:Tweet) = {
    val rep = RestClient.index { index into "monindex/testmonType" id tweet.id_str doc PlayJsonSource(tweet) }
    rep.map(println)
 }

}
