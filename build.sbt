scalaVersion := "2.11.5"

libraryDependencies ++= Seq("org.specs2" %% "specs2-core" % "2.4.15" % "test")
scalacOptions in Test ++= Seq("-Yrangepos")
resolvers ++= Seq("snapshots", "releases").map(Resolver.sonatypeRepo)
