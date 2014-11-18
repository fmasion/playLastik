val esVersion =  "1.4.0"

resolvers += "bintray" at "http://dl.bintray.com/fmasion/maven"

resolvers += "softprops-maven" at "http://dl.bintray.com/content/softprops/maven"

libraryDependencies ++= Seq(
// Add your project dependencies here
  "com.sksamuel.elastic4s" % "elastic4s_2.10" % esVersion,
  "me.lessis" %% "retry" % "0.2.0",
  "me.lessis" %% "odelay-netty3" % "0.1.0",
  ws
)

publishTo := Some("Fred's bintray" at "https://api.bintray.com/maven/fmasion/maven/playLastik")

publishMavenStyle := true

buildInfoSettings

buildInfoKeys := Seq[BuildInfoKey](name, version, scalaVersion, sbtVersion, "esVersion"->esVersion )

buildInfoPackage := "playlastik"

sourceGenerators in Compile <+= buildInfo
