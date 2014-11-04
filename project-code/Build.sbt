val esVersion =  "1.2.1.2"

libraryDependencies ++= Seq(
// Add your project dependencies here
  "com.sksamuel.elastic4s" % "elastic4s_2.10" % esVersion,
  ws
)

buildInfoSettings

buildInfoKeys := Seq[BuildInfoKey](name, version, scalaVersion, sbtVersion, "esVersion"->esVersion )

buildInfoPackage := "playlastik"

sourceGenerators in Compile <+= buildInfo
