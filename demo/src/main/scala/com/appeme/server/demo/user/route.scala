package com.appeme.server.demo.user.service

import akka.util._
import akka.pattern._
import spray.httpx.SprayJsonSupport
import spray.json.DefaultJsonProtocol
import spray.routing.{HttpService, Route}
import spray.routing.PathMatchers.Segment
import scala.concurrent.duration._
import scala.concurrent.Await
import com.mongodb.casbah.MongoClient
import com.appeme.server.demo.user.vo._

object UserJsonProtocol extends DefaultJsonProtocol with SprayJsonSupport{
  implicit val registerConvert = jsonFormat3(RegisterVo)
  implicit val passwordConvert = jsonFormat3(PasswordVo)
  implicit val updateConvert = jsonFormat10(UpdateVo)
}

trait UserService extends HttpService{
  import UserJsonProtocol._
  import com.appeme.server.demo.user.repositories.UserRepositories._

  val mongoClientFactory:MongoClient
  implicit def executeContext = actorRefFactory.dispatcher

  private val userRepositories = actorRefFactory.actorOf(props(mongoClientFactory,"dev-sht-service-v3"))
  implicit val timeout = Timeout(5 seconds)

  def register = (vo:RegisterVo) =>{
    val f = userRepositories ? vo
    Await.result(f, timeout.duration).asInstanceOf[String]
  }

  def update = (id:String, vo:UpdateVo) => {
    vo.id = Option(id)
    val f = userRepositories ? vo
    Await.result(f, timeout.duration).asInstanceOf[String]
  }

  def userRoute:Route = {
    path(Segment) { id =>
      post {
        entity(as[UpdateVo]){
          vo => complete(update(id, vo))
        }
      }
    } ~
    post{
        handleWith(register)
    }
  }
}



