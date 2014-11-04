  name         := "testPlayLastiK"

  version      := "1.0-SNAPSHOT"

  libraryDependencies ++= Seq(
    // Add your project dependencies here,
    "playlastik"  % "playlastik_2.10" % "1.2.1.1",
    ws
  )

  resolvers += Resolver.url("Fred's GitHub Play Repository", url("http://fmasion.github.com/releases/"))(Resolver.ivyStylePatterns)

lazy val testPlayLastiK = (project in file(".")).enablePlugins(PlayScala)

net.virtualvoid.sbt.graph.Plugin.graphSettings
