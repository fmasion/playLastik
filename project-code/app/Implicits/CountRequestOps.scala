package org.elasticsearch.action.count

/**
 * Created by fred on 07/11/14.
 */
object CountRequestOps {

    implicit class CountRequestOps(val that: CountRequest) extends AnyVal {
      //import scala.collection.JavaConversions._
      def queryString: String = Option(that.source()).map(_.toUtf8()).getOrElse("")
    }

}


