package playlastik.models

import play.api.libs.json._


abstract class BulkItem
case class IndexBulkItem(index: BulkItemDetail) extends BulkItem
case class CreateBulkItem(create: BulkItemDetail) extends BulkItem
case class UpdateBulkItem(update: BulkItemDetail) extends BulkItem
case class DeleteBulkItem(delete: BulkItemDetail) extends BulkItem

case class BulkItemDetail(_index:String,_type:String,_id:String,_version:Option[Long],status:Int, error:Option[String])

case class BulkResponse(took:Long,errors:Boolean,items:List[BulkItem])

object BulkItemDetail {
  implicit val indexBulkItemDetailFormat = Json.format[BulkItemDetail]
}
object IndexBulkItem {
  implicit val indexBulkItemFormat = Json.format[IndexBulkItem]
}
object CreateBulkItem {
  implicit val createBulkItemFormat = Json.format[CreateBulkItem]
}
object UpdateBulkItem {
  implicit val updateBulkItemFormat = Json.format[UpdateBulkItem]
}
object DeleteBulkItem {
  implicit val deleteBulkItemFormat = Json.format[DeleteBulkItem]
}

object BulkItem {
  implicit val bulkItemReads: Reads[BulkItem] =
    __.read[IndexBulkItem].map(x => x: BulkItem) orElse
    __.read[CreateBulkItem].map(x => x: BulkItem) orElse
    __.read[UpdateBulkItem].map(x => x: BulkItem) orElse
    __.read[DeleteBulkItem].map(x => x: BulkItem)

  implicit val bulkItemWrites = Writes[BulkItem] { cond =>
    cond match {
      case a: IndexBulkItem => IndexBulkItem.indexBulkItemFormat.writes(a)
      case a: CreateBulkItem => CreateBulkItem.createBulkItemFormat.writes(a)
      case a: UpdateBulkItem => UpdateBulkItem.updateBulkItemFormat.writes(a)
      case a: DeleteBulkItem => DeleteBulkItem.deleteBulkItemFormat.writes(a)
    }
  }
  implicit val BulkItemFormat = Format(bulkItemReads, bulkItemWrites)
}

object BulkResponse {
  implicit val bulkResponseFormat = Json.format[BulkResponse]
}
