package com.appeme.server.demo.user.vo

case class RegisterVo (mobile:String, name:String, password:String){
  require(mobile.trim.nonEmpty,"Mobile not empty")
  require(name.trim.nonEmpty, "Name not empty")
  require(password.trim.nonEmpty,"Password not empty")
  require(password.length >= 8, "Password length < 8");
}

case class UpdateVo (var id:Option[String], name:Option[String], icon:Option[String], email:Option[String],
                     company:Option[String], turnover:Option[String], industries:Option[Seq[String]],
                     positions:Option[Seq[String]], cities:Option[Seq[String]], address:Option[String])

case class PasswordVo(mobile:String, password:String, newPassword:String)