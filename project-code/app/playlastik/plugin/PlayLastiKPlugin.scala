package playlastik.plugin

import play.api._
import play.api.Play.current
import play.api._
import org.elasticsearch.client.transport.TransportClient
import org.elasticsearch.common.settings.ImmutableSettings
import org.elasticsearch.common.settings.SettingsException
import org.elasticsearch.common.transport.InetSocketTransportAddress
import org.elasticsearch.node.NodeBuilder._
import org.elasticsearch.node.Node
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest
import org.elasticsearch.client.Client
import playlastik.RestClient
import scala.concurrent.Await
import scala.concurrent.duration.Duration
import scala.concurrent.ExecutionContext.Implicits.global

class PlayLastiKPlugin(app: Application) extends Plugin {

  val isDev = Play.current.configuration.getBoolean("playLastiK.isDevMode").getOrElse(false)
  val cleanOnStop = Play.current.configuration.getBoolean("playLastiK.cleanOnStop").getOrElse(false)
  val clusterName = Play.current.configuration.getString("playLastiK.cleanOnStop").getOrElse("playLastiK")

  val log = Logger("playlastik.plugin.PlayLastiKPlugin")

  var oNode: Option[Node] = None

  override def onStart() {
    if (isDev) {
      log.info("Starting embeded ElasticSearch")

      val settingsBuilder = ImmutableSettings.settingsBuilder()
      val settings = settingsBuilder.put("cluster.name", clusterName).build()
      val nb = nodeBuilder().settings(settings).local(true).client(false).data(true)
      oNode = Option(nb.node())
      log.info("Ok Started embeded ElasticSearch")
      oNode.map { node =>
        val client = node.client()
        cleanIndices(client)
      }

    }

  }

  override def onStop() {
    log.info("Stoping embeded ElasticSearch")

    oNode.map { node =>
      val client = node.client()
      cleanIndices(client)
      client.close()
    }

    oNode.map { node =>
      node.close()
    }
    log.info("Ok Stoped embeded ElasticSearch")
  }

  def cleanIndices(client: Client) = {
    if (isDev && cleanOnStop) {
      val indicesNames = Await.result(RestClient.Admin.stats, Duration.create(1, "min")).getIndicesName
      log.info("Cleaning embeded ElasticSearch Indices : " + indicesNames)
      import scala.collection.JavaConversions._
      indicesNames.map(name => client.admin().indices().delete(new DeleteIndexRequest(name)))
    }
  }

}

