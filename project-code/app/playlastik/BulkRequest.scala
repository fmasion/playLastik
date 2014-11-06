package playlastik


import com.sksamuel.elastic4s.BulkCompatibleDefinition
import playlastik.dslHelper.BulkHelper


trait BulkRequest { this: WSimpl =>


  def bulk(reqs: BulkCompatibleDefinition*) = {
    val reqInfo = BulkHelper.getRequestInfo(serviceUrl, reqs: _*)
    doCall(reqInfo)
  }


}
