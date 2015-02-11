import sbt._
import Keys._

object BuildSettings  {
  val VERSION = "1.0"

  lazy  val basicSettings = seq(
      version := VERSION,
      homepage := Some(new URL("http://www.appeme.com")),
      organization := "appeme.com",
      organizationHomepage := Some(new URL("http://www.appeme.com")),
      description := "Appeme server program",
      startYear := Some(2015),
      scalaVersion := "2.11.5",
      scalacOptions := Seq(
        "-encoding", "utf8",
        "-feature",
        "-unchecked",
        "-deprecation",
        "-target:jvm-1.7",
        "-language:_",
        "-Xlog-reflective-calls"
      )
  )
}