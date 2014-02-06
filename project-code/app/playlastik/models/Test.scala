package playlastik.models
  import play.api.libs.json._
  
  case class Test2(p3:Long,p4:Int)
  case class Test(p1:String,p2:Int, p3: Map[String,Test2])
  
  object Test2 {
  implicit val test2format = Json.format[Test2]
  }
  object Test {
  	implicit val testformat = Json.format[Test]
  }
  
  
  