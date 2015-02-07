import NativePackagerKeys._

packageArchetype.java_application

name := """fortune-telling-robot"""
version := "1.0"

scalaVersion := "2.11.5"

// specs2
libraryDependencies ++= Seq("org.specs2" %% "specs2-core" % "2.4.15" % "test")
scalacOptions in Test ++= Seq("-Yrangepos")
resolvers ++= Seq("snapshots", "releases").map(Resolver.sonatypeRepo)

// Web stuff
libraryDependencies ++= Seq(
  "com.twitter" % "finagle-http_2.11" % "6.24.0",
  "oauth.signpost" % "signpost-core" % "1.2"
)
