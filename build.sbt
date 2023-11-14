name := """API"""
organization := "com.example"
version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.12"

libraryDependencies ++= Seq(
  guice,
  specs2 % Test,
  "org.scalatestplus.play" %% "scalatestplus-play" % "5.1.0" % Test,
  "org.postgresql" % "postgresql" % "42.5.4",
  "com.typesafe.play" %% "play-slick" % "5.1.0",
  "com.typesafe.play" %% "play-slick-evolutions" % "5.1.0",
  "io.swagger" % "swagger-core" % "1.6.11",
  "com.h2database" % "h2" % "2.1.214" % Test,
  "org.scalatest" %% "scalatest" % "3.2.15" % Test,
  "com.github.jwt-scala" %% "jwt-play-json" % "9.4.4"
)

ThisBuild / libraryDependencySchemes += "org.scala-lang.modules" %% "scala-xml" % VersionScheme.Always
ThisBuild / libraryDependencySchemes += "org.scala-lang.modules" %% "scala-java8-compat" % VersionScheme.Always

//libraryDependencies += "io.swagger" %% "swagger-play2" % "1.6.0" excludeAll (ExclusionRule("io.swagger", "swagger-scala-module"), ExclusionRule("io.swagger", "swagger-annotations"))
libraryDependencies += "io.swagger" %% "swagger-scala-module" % "1.0.6" exclude ("com.fasterxml.jackson.module", "jackson-module-scala")
libraryDependencies += "io.swagger" % "swagger-annotations" % "1.6.8"
libraryDependencies += "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.14.2"
//libraryDependencies += "org.webjars" %% "webjars-play" % "2.8.18"
//libraryDependencies += "org.webjars" % "swagger-ui" % "4.18.1"
