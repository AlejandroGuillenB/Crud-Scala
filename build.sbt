name := """API"""
organization := "com.example"
version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.12"

libraryDependencies ++= Seq(
  guice,
  "org.scalatestplus.play" %% "scalatestplus-play" % "5.1.0" % Test,
  "org.postgresql" % "postgresql" % "42.5.4",
  "com.typesafe.play" %% "play-slick" % "5.1.0",
  "com.typesafe.play" %% "play-slick-evolutions" % "5.1.0",
  "io.swagger" % "swagger-core" % "1.5.24",
  "io.swagger" %% "swagger-scala-module" % "1.0.6",
)
resolvers += "Atlassian's Maven Public Repository" at "https://packages.atlassian.com/maven-public/"

ThisBuild / libraryDependencySchemes += "org.scala-lang.modules" %% "scala-xml" % VersionScheme.Always
ThisBuild / libraryDependencySchemes += "org.scala-lang.modules" %% "scala-java8-compat" % VersionScheme.Always
