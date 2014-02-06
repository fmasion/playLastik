package models

import play.api.libs.json.Json

case class TwitterHashtag(indices: List[Int], text: String)
case class TwitterMedia(id_str: String, indices: List[Int], media_url: String)
case class TwitterMention(id: Long, id_str: String, indices: List[Int], name: String, screen_name: String)
case class TwitterUrl(display_url: String, expanded_url: String, indices: List[Int], url: String)
case class TwitterEntities(hashtags: List[TwitterHashtag], urls: List[TwitterUrl], user_mentions: List[TwitterMention], media: Option[List[TwitterMedia]]) 
case class TwitterCoordinates(`type`: String, coordinates: List[Float])
case class Tweet(id_str: String,user:TwitterUser, text: String, created_at: String, lang: String, entities: Option[TwitterEntities], coordinates : Option[TwitterCoordinates], in_reply_to_user_id_str:Option[String], retweet_count:Long, source:String)
case class TwitterUser(name:String, screen_name:String, id_str:String, friends_count:Double, profile_image_url:Option[String], created_at:String, location:String, url:Option[String], profile_image_url_https:String, id:Long, lang:String, followers_count:Long, profile_background_image_url_https:String, profile_background_color:String, verified:Boolean, geo_enabled:Boolean, time_zone:Option[String], description:Option[String])


object Tweet {
  
	implicit val hashtagReads = Json.format[TwitterHashtag]
	implicit val urlReads = Json.format[TwitterUrl]
	implicit val mentionReads = Json.format[TwitterMention]
	implicit val mediasReaders = Json.format[TwitterMedia]
	implicit val entitiesReaders = Json.format[TwitterEntities]
	implicit val coordinatesReaders = Json.format[TwitterCoordinates]
	implicit val userReaders = Json.format[TwitterUser]
	implicit val tweetReaders = Json.format[Tweet]

}