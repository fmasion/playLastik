val esVersion =  "1.2.1.2"

resolvers += "bintray" at "http://dl.bintray.com/fmasion/maven"

libraryDependencies ++= Seq(
// Add your project dependencies here
  "com.sksamuel.elastic4s" % "elastic4s_2.10" % esVersion,
  ws
)

publishTo := Some("Fred's bintray" at "https://api.bintray.com/maven/fmasion/maven/playLastik")

publishMavenStyle := true

buildInfoSettings

buildInfoKeys := Seq[BuildInfoKey](name, version, scalaVersion, sbtVersion, "esVersion"->esVersion )

buildInfoPackage := "playlastik"

sourceGenerators in Compile <+= buildInfo
