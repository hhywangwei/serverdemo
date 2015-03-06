import sbt._
import Keys._

object Build extends Build {
  import BuildSettings._
  import Dependencies._

  lazy val root = Project("root",file(".")).aggregate("demo")
    .settings(basicSettings:_*)
    .dependsOn("demo")

  lazy val demo = Project("demo", file("demo"))
    .settings(basicSettings:_*)
    .settings(
       libraryDependencies ++=
           compile(scalaLogging, scalaXml, akkaSlf4j, akka, logback, sprayCan, sprayHttpx, sprayRouting, sprayJson, mongo) ++
           test(scalaTest, scalaMock, junit,scalaCheck)
    )
}


