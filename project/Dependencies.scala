import sbt._

object Dependencies{

  def compile   (deps: ModuleID*): Seq[ModuleID] = deps map (_ % "compile")
  def provided  (deps: ModuleID*): Seq[ModuleID] = deps map (_ % "provided")
  def test      (deps: ModuleID*): Seq[ModuleID] = deps map (_ % "test")
  def runtime   (deps: ModuleID*): Seq[ModuleID] = deps map (_ % "runtime")
  def container (deps: ModuleID*): Seq[ModuleID] = deps map (_ % "container")

  val scalaLogging = "com.typesafe.scala-logging" %% "scala-logging" % "3.1.0"
  val scalaXml = "org.scala-lang.modules" %% "scala-xml" % "1.0.2"
  val akka = "com.typesafe.akka" %% "akka-actor" % "2.3.9"
  val akkaSlf4j = "com.typesafe.akka" %% "akka-slf4j" % "2.3.9"
  val logback = "ch.qos.logback" % "logback-classic" % "1.0.13"
  val sprayCan = "io.spray" %% "spray-can" % "1.3.2"
  val sprayRouting = "io.spray" %% "spray-routing" % "1.3.2"
  val sprayHttpx = "io.spray" %% "spray-httpx" % "1.3.2"
  val sprayJson =  "io.spray" %%  "spray-json" % "1.3.1"
  val mongo = "org.mongodb" %% "casbah" % "2.7.4"
  val scalaTest = "org.scalatest" %% "scalatest" % "2.2.1"
  val scalaMock = "org.scalamock" %% "scalamock-scalatest-support" % "3.2"
  val scalaCheck = "org.scalacheck" %% "scalacheck" % "1.12.2"
  val junit = "junit" % "junit" % "4.12"
}