import sbt._

object DockerizePingBuild extends Build {
  lazy val root = Project("dockerize-ping", file(".")) dependsOn docker
  lazy val docker = file("../..").getAbsoluteFile.toURI
}