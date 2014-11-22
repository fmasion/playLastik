lazy val playLastik = (project in file("project-code")).enablePlugins(PlayScala)

lazy val playLastikSample = (project in file("samples")).enablePlugins(PlayScala).dependsOn(playLastik)

lazy val playLastikParent = (project in file(".")).aggregate(playLastik,playLastikSample)

version in ThisBuild := "1.4.0-SNAPSHOT"

publishArtifact := false
