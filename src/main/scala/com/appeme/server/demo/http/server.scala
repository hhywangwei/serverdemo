package com.appeme.server.demo.http

import akka.actor.{ActorLogging,Props}
import com.appeme.server.demo.project.ProjectService
import com.appeme.server.demo.user.service._
import com.mongodb.casbah.MongoClient
import spray.routing.{HttpServiceActor, Route}

/**
 * 服务Actor
 *
 * @author WangWei
 */
object ServiceActor{
  def props(mongoClient: MongoClient) = Props(new ServiceActor(mongoClient))
}
class ServiceActor(mongoClient: MongoClient) extends HttpServiceActor
    with ActorLogging with UserService with ProjectService{

  lazy val mongoClientFactory = mongoClient
  def receive = runRoute(mainRoute)

  def mainRoute:Route = {
    (get|post|delete|put){
      pathPrefix("user"){
        log.info("user --> route")
        userRoute
      }~
      pathPrefix("project"){
        log.info("project --> route")
        projectRoute
      }
    }
  }
}
