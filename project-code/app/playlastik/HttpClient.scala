//package playlastik
//
//import play.api.Logger
//import play.api._
//import play.api.Play.current
//import play.api.libs.ws.WS
//import scala.concurrent.Future
//import play.api.libs.json._
//import play.api.libs.ws.WSResponse
//
//object HttpClient {
//
//  val log = Logger("playlastik.HttpClient")
//  val serviceUrl = Play.configuration.getString("playLastiK.url").getOrElse("http://localhost:9200")
//
//  //  /**
//  //   * Use the bulk API to perform many index/delete operations in a single call.
//  //   *
//  //   * @param index The optional index name.
//  //   * @param type The optional type.
//  //   * @param data The operations to perform as described by the [[http://www.elasticsearch.org/guide/en/elasticsearch/reference/current/docs-bulk.html ElasticSearch Bulk API]].
//  //   */
//  //  def bulk(index: Option[String] = None, `type`: Option[String] = None, data: String): Future[Response] = {
//  //    val freq = (url(esURL) / index.getOrElse("") / `type`.getOrElse("") / "_bulk").setBody(data.getBytes(StandardCharsets.UTF_8))
//  //    doRequest(freq.POST)
//  //  }
//
//  object Index {
//    /**
//     * Create an index, optionally using the supplied settings.
//     *
//     * @param name The name of the index.
//     * @param settings Optional settings in JsValue Format
//     */
//    def createIndex(name: String, settings: Option[JsValue] = None): Future[WSResponse] = {
//      val url = serviceUrl + s"/${name}/"
//      settings match {
//        case Some(s) => WS.url(url).put(s)
//        case None => WS.url(url).put("")
//      }
//    }
//
//    /**
//     * Delete an index
//     *
//     * @param name The name of the index to delete.
//     */
//    def deleteIndex(name: String): Future[WSResponse] = {
//      val url = serviceUrl + s"/${name}"
//      WS.url(url).delete
//    }
//
//    /**
//     * Refresh an index.
//     *
//     * Makes all operations performed since the last refresh available for search.
//     * @param index Name of the index to refresh
//     */
//    def refresh(index: String) = {
//      val url = serviceUrl + s"/${index}"
//      WS.url(url).post("")
//    }
//
//  }
//
//  object Document {
//
//    /**
//     * Index a document.
//     *
//     * Adds or updates a JSON documented of the specified type in the specified
//     * index.
//     * @param index The index in which to place the document
//     * @param type The type of document to be indexed
//     * @param id The id of the document. Specifying None will trigger automatic ID generation by ElasticSearch
//     * @param data The document to index, which should be a JSON string
//     * @param refresh If true then ElasticSearch will refresh the index so that the indexed document is immediately searchable.
//     */
//    def index(index: String, `type`: String, id: Option[String] = None, data: JsValue, refresh: Boolean = false): Future[WSResponse] = {
//      // XXX Need to add parameters: version, op_type, routing, parents & children,
//      // timestamp, ttl, percolate, timeout, replication, consistency
//
//      val baseurl = serviceUrl + s"/${index}/${`type`}"
//      val url = id.map(id => baseurl + "/${id}").getOrElse(baseurl)
//      val reqholder = WS.url(url).withQueryString("refresh" -> { if (refresh) { "true" } else { "false" } })
//      id.map(i => reqholder.put(data)).getOrElse(reqholder.post(data))
//    }
//
//    //    /**
//    //     * Index a document.
//    //     *
//    //     * Adds or updates a JSON documented of the specified type in the specified
//    //     * index.
//    //     * @param index The index in which to place the document
//    //     * @param type The type of document to be indexed
//    //     * @param id The id of the document. Specifying None will trigger automatic ID generation by ElasticSearch
//    //     * @param data The case class to index, which should have a Writer
//    //     * @param refresh If true then ElasticSearch will refresh the index so that the indexed document is immediately searchable.
//    //     * @param implicit Writes to transform the case class to json Object
//    //     */
//    //    def index[T](index: String, `type`: String, id: Option[String] = None, data: T, refresh: Boolean = false)(implicit fjs: Writes[T]): Future[Response] = {
//    //      Document.index(index, `type`, id, fjs.writes(data), refresh)
//    //    }
//
//    /**
//     * Get a document by ID.
//     *
//     * @param index The name of the index.
//     * @param type The type of the document.
//     * @param id The id of the document.
//     */
//    def get(index: String, `type`: String, id: String): Future[WSResponse] = {
//      val url = serviceUrl + s"/${index}/${`type`}/${id}"
//      WS.url(url).get()
//    }
//
//    /**
//     * Delete a document from the index.
//     *
//     * @param index The name of the index.
//     * @param type The type of document to delete.
//     * @param id The ID of the document.
//     */
//    def delete(index: String, `type`: String, id: String): Future[WSResponse] = {
//      // XXX Need to add parameters: version, routing, parent, replication,
//      // consistency, refresh
//      val url = serviceUrl + s"/${index}/${`type`}/${id}/"
//      WS.url(url).delete()
//    }
//
//    /**
//     * Delete documents that match a query.
//     *
//     * @param indices A sequence of index names for which mappings will be fetched.
//     * @param types A sequence of types for which mappings will be fetched.
//     * @param query The query to count documents from.
//     */
//    def deleteByQuery(indices: Seq[String], `types`: Seq[String], query: JsValue): Future[WSResponse] = {
//      // XXX Need to add parameters: df, analyzer, default_operator
//      val url = serviceUrl + s"""/${indices.mkString(",")}/${types.mkString(",")}/_query/"""
//      WS.url(url).withBody(query).delete()
//    }
//
//  }
//
//  object Alias {
//
//    /**
//     * Create aliases.
//     *
//     * @param actions A JSON Object containing the actions to be performed. This string will be placed within the actions array passed
//     *
//     * As defined in the [[http://www.elasticsearch.org/guide/reference/api/admin-indices-aliases/ ElasticSearch Admin Indices API]] this
//     * method takes a string representing a list of operations to be performed. Remember to
//     *
//     * { "actions": [{ "add": { "index": "index1", "alias": "alias1" } }, { "add": { "index": "index2", "alias": "alias2" } }]
//     *
//     */
//    def createAlias(actions: JsValue): Future[WSResponse] = {
//      val url = serviceUrl + """/_aliases"""
//      WS.url(url).post(actions)
//    }
//
//    /**
//     * Delete an index alias.
//     *
//     * @param index The name of the index.
//     * @param alias The name of the alias.
//     */
//    def deleteAlias(index: String, alias: String): Future[WSResponse] = {
//      val url = serviceUrl + s"/${index}/_alias/${alias}"
//      WS.url(url).delete()
//    }
//
//    /**
//     * Get aliases for indices.
//     *
//     * @param index Optional name of an index. If no index is supplied, then the query will check all indices.
//     * @param query The name of alias to return in the response. Like the index option, this option supports wildcards and the option the specify multiple alias names separated by a comma.
//     */
//    def getAliases(index: Option[String], query: String = "*"): Future[WSResponse] = {
//      val url = index.map(i => serviceUrl + s"/${i}").getOrElse(serviceUrl) + s"/_alias/${query}"
//      WS.url(url).get()
//    }
//
//  }
//
//  object Query {
//    /**
//     * Search for documents.
//     *
//     * @param index The index to search
//     * @param query The query to execute.
//     */
//    def search(index: String, query: JsValue): Future[WSResponse] = {
//      val url = serviceUrl + s"/${index}/_search"
//      WS.url(url).post(query)
//    }
//
//    /**
//     * Request a count of the documents matching a query.
//     *
//     * @param indices A sequence of index names for which mappings will be fetched.
//     * @param types A sequence of types for which mappings will be fetched.
//     * @param query The query to count documents from.
//     */
//    def count(indices: Seq[String], types: Seq[String], query: JsValue): Future[WSResponse] = {
//      val url = serviceUrl + s"/${indices.mkString(",")}/${types.mkString(",")}/_count"
//      WS.url(url).withBody(query).get()
//    }
//
//    /**
//     * Validate a query.
//     *
//     * @param index The name of the index.
//     * @param type The optional type of document to validate against.
//     * @param query The query.
//     * @param explain If true, then the response will contain more detailed information about the query.
//     */
//    def validate(index: String, `type`: Option[String] = None, query: JsValue, explain: Boolean = false): Future[WSResponse] = {
//      val url = serviceUrl + s"/${index + `type`.map(t => s"/${t}").getOrElse("")}/_validate/query"
//      WS.url(url).post(query)
//    }
//
//    /**
//     * Explain a query and document.
//     *
//     * @param index The name of the index.
//     * @param type The optional type document to explain.
//     * @param id The ID of the document.
//     * @param query The query.
//     * @param explain If true, then the response will contain more detailed information about the query.
//     */
//    def explain(index: String, `type`: String, id: String, query: JsValue): Future[WSResponse] = {
//      // XXX Lots of params to add
//      val url = serviceUrl + s"/${index}/${`type`}/${id}/_explain"
//      WS.url(url).post(query)
//    }
//
//  }
//
//  object Mapping {
//
//    /**
//     * Put a mapping for a list of indices.
//     *
//     * @param indices A sequence of index names for which mappings will be added.
//     * @param type The type name to which the mappings will be applied.
//     * @param body The mapping.
//     */
//    def putMapping(indices: Seq[String], `type`: String, body: String): Future[WSResponse] = {
//      val url = serviceUrl + s"${indices.mkString(",")}/${`type`}/_mapping"
//      WS.url(url).put(body)
//    }
//
//    /**
//     * Get the mappings for a list of indices.
//     *
//     * @param indices A sequence of index names for which mappings will be fetched.
//     * @param types A sequence of types for which mappings will be fetched.
//     */
//    def getMapping(indices: Seq[String], types: Seq[String]): Future[WSResponse] = {
//      val url = serviceUrl + s"${indices.mkString(",")}/${types.mkString(",")}/_mapping"
//      WS.url(url).get()
//    }
//  }
//
//  object System {
//    /**
//     * Query ElasticSearch for it's health.
//     *
//     * @param indices Optional list of index names. Defaults to empty.
//     * @param level Can be one of cluster, indices or shards. Controls the details level of the health information returned.
//     * @param waitForStatus One of green, yellow or red. Will wait until the status of the cluster changes to the one provided, or until the timeout expires.
//     * @param waitForRelocatingShards A number controlling to how many relocating shards to wait for.
//     * @param waitForNodes The request waits until the specified number N of nodes is available. Is a string because >N and ge(N) type notations are allowed.
//     * @param timeout A time based parameter controlling how long to wait if one of the waitForXXX are provided.
//     */
//    def health(
//      indices: Seq[String] = Seq.empty[String], level: Option[String] = None,
//      waitForStatus: Option[String] = None, waitForRelocatingShards: Option[String] = None,
//      waitForNodes: Option[String] = None, timeout: Option[String] = None): Future[WSResponse] = {
//      val url = serviceUrl + """_cluster/health/${indices.mkString(",")}"""
//      val reqHolder = WS.url(url)
//
//      val paramNames = List("level", "wait_for_status", "wait_for_relocation_shards", "wait_for_nodes", "timeout")
//      val params = List(level, waitForStatus, waitForRelocatingShards, waitForNodes, timeout)
//
//      // Fold in all the parameters that might've come in.
//      val reqHolderWithParam = paramNames.zip(params).foldLeft(reqHolder)(
//        (r, nameAndParam) => {
//          nameAndParam._2.map({ p =>
//            r.withQueryString(nameAndParam._1 -> p)
//          }).getOrElse(r)
//        })
//      reqHolderWithParam.get()
//    }
//
//    /**
//     * Query ElasticSearch Stats. Parameters to enable non-default stats as desired.
//     *
//     * @param indices Optional list of index names. Defaults to empty.
//     * @param clear Clears all the flags (first).
//     * @param refresh refresh stats.
//     * @param flush flush stats.
//     * @param merge merge stats.
//     * @param warmer Warmer statistics.
//     */
//    def stats(indices: Seq[String] = Seq(), clear: Boolean = false, refresh: Boolean = false, flush: Boolean = false, merge: Boolean = false, warmer: Boolean = false): Future[WSResponse] = {
//      val url = indices match {
//        case Nil => serviceUrl + """/_stats"""
//        case _ => serviceUrl + s"""/${indices.mkString(",")}/_stats"""
//      }
//      val reqHolder = WS.url(url)
//      val paramNames = List("clear", "refresh", "flush", "merge", "warmer")
//      val params = List(clear, refresh, flush, merge, warmer)
//      val reqHolderWithParams = paramNames.zip(params).filter(_._2).foldLeft(reqHolder)((r, nameAndParam) => r.withQueryString(nameAndParam._1 -> nameAndParam._2.toString))
//      reqHolderWithParams.get
//    }
//
//    /**
//     * Verify that an index exists.
//     *
//     * @param name The name of the index to verify.
//     */
//    def verifyIndex(name: String): Future[WSResponse] = {
//      val url = serviceUrl + s"/${name}"
//      WS.url(url).head
//    }
//
//    /**
//     * Verify that a type exists.
//     *
//     * @param name The name of the type to verify.
//     */
//    def verifyType(index: String, `type`: String): Future[WSResponse] = {
//      val url = serviceUrl + s"/${index}/${`type`}"
//      WS.url(url).head
//    }
//
//  }
//
//}
