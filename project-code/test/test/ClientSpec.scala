package test

import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._
import scala.concurrent.Await
import scala.concurrent.duration._
import playlastik._
import playlastik.HttpClient
import playlastik.plugin.PlayLastiKPlugin
import play.api.test._
import play.api.test.Helpers._

@RunWith(classOf[JUnitRunner])
class HttpClientSpec extends Specification {

//  sequential
//
//  "Client" should {
//    running(FakeApplication(additionalConfiguration = inMemoryDatabase())) {
//      val app = play.api.Play.current
//      val mockES = new PlayLastiKPlugin(app)
//      mockES.onStart
//
//      "fail usefully" in {
//
//        val res = Await.result(HttpClient.System.verifyIndex("foobarbaz"), Duration(1, "second"))
//        res.status must beEqualTo(404)
//
//        1 must beEqualTo(1)
//      }

      //    "create and delete indexes" in {
      //      val HttpClient = new HttpClient("http://localhost:9200")
      //
      //      Await.result(HttpClient.createIndex(name = "foo"), Duration(1, "second")).getResponseBody must contain("acknowledged")
      //
      //      Await.result(HttpClient.verifyIndex("foo"), Duration(1, "second"))
      //
      //      Await.result(HttpClient.deleteIndex("foo"), Duration(1, "second")).getResponseBody must contain("acknowledged")
      //
      //      1 must beEqualTo(1)
      //    }
      //
      //    "create and delete aliases" in {
      //      val HttpClient = new HttpClient("http://localhost:9200")
      //
      //      Await.result(HttpClient.createIndex(name = "foo"), Duration(1, "second")).getResponseBody must contain("acknowledged")
      //
      //      Await.result(HttpClient.createAlias(actions = """{ "add": { "index": "foo", "alias": "foo-write" }}"""), Duration(1, "second")).getResponseBody must contain("acknowledged")
      //
      //      Await.result(HttpClient.getAliases(index = Some("foo")), Duration(1, "second")).getResponseBody must contain("foo-write")
      //
      //      Await.result(HttpClient.deleteAlias(index = "foo", alias = "foo-write"), Duration(1, "second")).getResponseBody must contain("acknowledged")
      //
      //      Await.result(HttpClient.deleteIndex("foo"), Duration(1, "second")).getResponseBody must contain("acknowledged")
      //
      //      1 must beEqualTo(1)
      //    }
      //
      //    "index and fetch a document" in {
      //      val HttpClient = new HttpClient("http://localhost:9200")
      //
      //      Await.result(HttpClient.index(
      //        id = Some("foo"),
      //        index = "foo", `type` = "foo",
      //        data = "{\"foo\":\"bar₡\"}", refresh = true
      //      ), Duration(1, "second")).getResponseBody must contain("\"_version\"")
      //
      //      Await.result(HttpClient.get("foo", "foo", "foo"), Duration(1, "second")).getResponseBody must contain("\"bar₡\"")
      //
      //      Await.result(HttpClient.delete("foo", "foo", "foo"), Duration(1, "second")).getResponseBody must contain("\"found\"")
      //
      //      1 must beEqualTo(1)
      //    }
      //
      //    "index and search for a document" in {
      //      val HttpClient = new HttpClient("http://localhost:9200")
      //
      //      Await.result(HttpClient.index(
      //        index = "foo", `type` = "foo", id = Some("foo2"),
      //        data = "{\"foo\":\"bar\"}", refresh = true
      //      ), Duration(1, "second")).getResponseBody must contain("\"_version\"")
      //
      //      Await.result(HttpClient.search("foo", "{\"query\": { \"match_all\": {} } }"), Duration(1, "second")).getResponseBody must contain("\"foo2\"")
      //
      //      Await.result(HttpClient.count(Seq("foo"), Seq("foo"), "{\"query\": { \"match_all\": {} }"), Duration(1, "second")).getResponseBody must contain("\"count\"")
      //
      //      Await.result(HttpClient.delete("foo", "foo", "foo2"), Duration(1, "second")).getResponseBody must contain("\"found\"")
      //
      //      Await.result(HttpClient.deleteIndex("foo"), Duration(1, "second")).getResponseBody must contain("acknowledged")
      //
      //      1 must beEqualTo(1)
      //    }
      //
      //    "delete a document by query" in {
      //      val HttpClient = new HttpClient("http://localhost:9200")
      //
      //      Await.result(HttpClient.index(
      //        index = "foo", `type` = "foo", id = Some("foo2"),
      //        data = "{\"foo\":\"bar\"}", refresh = true
      //      ), Duration(1, "second")).getResponseBody must contain("\"_version\"")
      //
      //      Await.result(HttpClient.count(Seq("foo"), Seq("foo"), "{\"query\": { \"match_all\": {} }"), Duration(1, "second")).getResponseBody must contain("\"count\":1")
      //
      //      Await.result(HttpClient.deleteByQuery(Seq("foo"), Seq.empty[String], "{ \"match_all\": {} }"), Duration(1, "second")).getResponseBody must contain("\"successful\"")
      //
      //      Await.result(HttpClient.count(Seq("foo"), Seq("foo"), "{\"query\": { \"match_all\": {} }"), Duration(1, "second")).getResponseBody must contain("\"count\":0")
      //
      //      Await.result(HttpClient.deleteIndex("foo"), Duration(1, "second")).getResponseBody must contain("acknowledged")
      //
      //      1 must beEqualTo(1)
      //    }
      //
      //    "properly manipulate mappings" in {
      //      val HttpClient = new HttpClient("http://localhost:9200")
      //
      //      Await.result(HttpClient.createIndex(name = "foo"), Duration(1, "second")).getResponseBody must contain("acknowledged")
      //
      //      Await.result(HttpClient.putMapping(Seq("foo"), "foo", "{\"tweet\": { \"properties\": { \"message\": { \"type\": \"string\", \"store\": \"yes\" } } } }"), Duration(1, "second")).getResponseBody must contain("acknowledged")
      //
      //      Await.result(HttpClient.verifyType("foo", "foo"), Duration(1, "second"))
      //
      //      Await.result(HttpClient.getMapping(Seq("foo"), Seq("foo")), Duration(1, "second")).getResponseBody must contain("store")
      //
      //      Await.result(HttpClient.deleteIndex("foo"), Duration(1, "second")).getResponseBody must contain("acknowledged")
      //
      //      1 must beEqualTo(1)
      //    }
      //
      //    "validate and explain queries" in {
      //      val HttpClient = new HttpClient("http://localhost:9200")
      //
      //      Await.result(HttpClient.createIndex(name = "foo"), Duration(1, "second")).getResponseBody must contain("acknowledged")
      //
      //      Await.result(HttpClient.index(
      //        index = "foo", `type` = "foo", id = Some("foo2"),
      //        data = "{\"foo\":\"bar\"}", refresh = true
      //      ), Duration(1, "second")).getResponseBody must contain("\"_version\"")
      //
      //      Await.result(HttpClient.validate(index = "foo", query = "{\"query\": { \"match_all\": {} }"), Duration(1, "second")).getResponseBody must contain("\"valid\"")
      //
      //      Await.result(HttpClient.explain(index = "foo", `type` = "foo", id = "foo2", query = "{\"query\": { \"term\": { \"foo\":\"bar\"} } }"), Duration(1, "second")).getResponseBody must contain("explanation")
      //
      //      Await.result(HttpClient.deleteIndex("foo"), Duration(1, "second")).getResponseBody must contain("acknowledged")
      //
      //      1 must beEqualTo(1)
      //    }
      //
      //    "handle health checking" in {
      //      val HttpClient = new HttpClient("http://localhost:9200")
      //
      //      Await.result(HttpClient.health(), Duration(1, "second")).getResponseBody must contain("number_of_nodes")
      //
      //      Await.result(HttpClient.health(level = Some("indices"), timeout = Some("5")), Duration(1, "second")).getResponseBody must contain("number_of_nodes")
      //
      //      1 must beEqualTo(1)
      //    }
      //
      //    "handle stats checking" in {
      //      val HttpClient = new HttpClient("http://localhost:9200")
      //
      //      Await.result(HttpClient.createIndex(name = "foo"), Duration(1, "second")).getResponseBody must contain("acknowledged")
      //      Await.result(HttpClient.createIndex(name = "bar"), Duration(1, "second")).getResponseBody must contain("acknowledged")
      //
      //      val res = Await.result(HttpClient.stats(), Duration(1, "second")).getResponseBody
      //      res must contain("\"ok\":true")
      //      res must contain("_all")
      //      res must contain("indices")
      //      res must contain("foo")
      //      res must contain("bar")
      //
      //      val fooRes = Await.result(HttpClient.stats(indices = Seq("foo")), Duration(1, "second")).getResponseBody
      //      fooRes must contain("\"ok\":true")
      //      fooRes must contain("_all")
      //      fooRes must contain("indices")
      //      fooRes must contain("foo")
      //      fooRes must not contain("bar")
      //
      //      val barRes = Await.result(HttpClient.stats(indices = Seq("bar")), Duration(1, "second")).getResponseBody
      //      barRes must contain("\"ok\":true")
      //      barRes must contain("_all")
      //      barRes must contain("indices")
      //      barRes must contain("bar")
      //      barRes must not contain("foo")
      //
      //      Await.result(HttpClient.deleteIndex("foo"), Duration(1, "second")).getResponseBody must contain("acknowledged")
      //      Await.result(HttpClient.deleteIndex("bar"), Duration(1, "second")).getResponseBody must contain("acknowledged")
      //    }
      //
      //    "handle bulk requests" in {
      //      val HttpClient = new HttpClient("http://localhost:9200")
      //
      //      val res = Await.result(HttpClient.bulk(data = """{ "index" : { "_index" : "test", "_type" : "type1", "_id" : "1" } }
      //{ "field1" : "value1" }
      //{ "delete" : { "_index" : "test", "_type" : "type1", "_id" : "2" } }
      //{ "create" : { "_index" : "test", "_type" : "type1", "_id" : "3" } }
      //{ "field1" : "value3" }
      //{ "update" : {"_id" : "1", "_type" : "type1", "_index" : "index1"} }
      //{ "doc" : {"field2" : "value2"} }"""), Duration(1, "second")).getResponseBody
      //      res must contain("\"ok\":true")
      //
      //      Await.result(HttpClient.deleteIndex("test"), Duration(1, "second")).getResponseBody must contain("acknowledged")
      //    }

//      mockES.onStop
//    }
//  }
}