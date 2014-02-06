  name         := "testPlayLastiK"
  
  version      := "1.0-SNAPSHOT"

  libraryDependencies ++= Seq(
    // Add your project dependencies here,
    javaCore,
    "playlastik"  % "playlastik_2.10" % "0.90.10.2"
  )

  resolvers += Resolver.url("Fred's GitHub Play Repository", url("http://fmasion.github.com/releases/"))(Resolver.ivyStylePatterns)

  play.Project.playScalaSettings

net.virtualvoid.sbt.graph.Plugin.graphSettings
