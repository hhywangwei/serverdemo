package com.appeme.server.demo

import akka.actor.{ActorSystem, Props}
import akka.io.IO
import spray.can.Http
import com.appeme.server.demo.config.MongoConfigure
import com.appeme.server.demo.http.ServiceActor._

/**
 * 程序入口
 *
 * @author WangWei
 */
object MainApp extends App with MongoConfigure {
  implicit lazy val system = ActorSystem("appeme")
  val handler = system.actorOf(props(mongoClient), name="server")
  IO(Http) ! Http.Bind(handler, interface = "localhost", port = 8080)
}
