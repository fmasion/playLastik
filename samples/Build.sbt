resolvers += "fred's bintray" at "http://dl.bintray.com/fmasion/maven"

libraryDependencies ++= Seq(
  // Add your project dependencies here,
  "playlastik"  % "playlastik_2.10" % "1.7.0.1",
  ws
)

publishArtifact := false
