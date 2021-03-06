package com.appeme.server.demo.user.service

import akka.util._
import akka.pattern._
import spray.http.StatusCodes._
import spray.httpx.SprayJsonSupport
import spray.json.DefaultJsonProtocol
import spray.routing._
import scala.concurrent.duration._
import scala.concurrent.Await
import com.mongodb.casbah.MongoClient
import com.appeme.server.demo.vo._
import com.appeme.server.demo.user.vo._

object UserJsonProtocol extends DefaultJsonProtocol with SprayJsonSupport{
  implicit  val errorConvert = jsonFormat2(ErrorVo)
  implicit val registerConvert = jsonFormat3(RegisterVo)
  implicit val passwordConvert = jsonFormat3(PasswordVo)
  implicit val updateConvert = jsonFormat10(UpdateVo)
}

trait UserService extends HttpService {
  import UserJsonProtocol._
  import com.appeme.server.demo.user.repositories.UserRepositories._

  implicit val myRejectionHandler = RejectionHandler {
    case MalformedRequestContentRejection(msg, _) :: _ ⇒ complete(BadRequest, new ErrorVo(1, msg))
  }

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
    post{
      path(Segment) {
        id => entity(as[UpdateVo]){
          vo => {
            complete(OK,update(id, vo))
          }
        }
      } ~ {
        entity(as[RegisterVo]){
          vo => {
            complete(register(vo))
          }
        }
      }
    }
  }
}



