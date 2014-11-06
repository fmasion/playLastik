package playlastik.test

import java.io.File
import java.util.UUID

import com.sksamuel.elastic4s.ElasticDsl._
import org.elasticsearch.common.settings.ImmutableSettings
import org.elasticsearch.indices.IndexMissingException
import org.elasticsearch.node.Node
import org.elasticsearch.node.NodeBuilder._
import org.specs2.specification.BeforeExample
import play.api.Logger


object TestElasticNode {
  val log = Logger("TestElasticNode")
  var oNode: Option[Node] = None

  val tempFile = File.createTempFile("elasticsearchtests", "tmp")
  val homeDir = new File(tempFile.getParent + "/" + UUID.randomUUID().toString)
  homeDir.mkdir()
  homeDir.deleteOnExit()
  tempFile.deleteOnExit()
  log.info(s"Setting ES home dir [${homeDir}]")

  val settings = ImmutableSettings.settingsBuilder()
    .put("node.http.enabled", false)
    .put("http.enabled", true)
    .put("path.home", homeDir.getAbsolutePath)
    .put("index.number_of_shards", 1)
    .put("index.number_of_replicas", 0)
    .put("script.disable_dynamic", false)
    .put("es.logger.level", "DEBUG")

  val nb = nodeBuilder().settings(settings).local(true).client(false).data(true)


  def start = oNode = Option(nb.node())

  def stop = oNode.map(_.close())

//  implicit val client = ElasticClient.local(settings.build)
}


trait WithExternalES {

  def startES = TestElasticNode.start

  def stopES = TestElasticNode.stop
}
