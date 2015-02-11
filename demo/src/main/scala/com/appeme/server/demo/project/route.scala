package com.appeme.server.demo.project

import com.appeme.server.demo.project.vo._
import spray.httpx.SprayJsonSupport
import spray.json.DefaultJsonProtocol
import spray.routing._

object ProjectJsonProtocol extends DefaultJsonProtocol with SprayJsonSupport{
  implicit val projectConvert = jsonFormat5(ProjectVo)
}

trait ProjectService extends HttpService{
  import ProjectJsonProtocol._

  def create = (vo:ProjectVo) =>{
    vo
  }

  def projectRoute:Route = {
    post{
      handleWith(create)
    }
  }
}
