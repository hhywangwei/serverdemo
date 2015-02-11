package com.appeme.server.demo.project

case class UserMin(id:String, name:String, icon:String)

case class Project(id:String , user:UserMin, ctg:String, content:String, images:Option[Seq[String]])
