package com.appeme.server.demo.user.vo

case class RegisterVo (mobile:String, name:String, password:String)

case class UpdateVo (var id:Option[String], name:Option[String], icon:Option[String], email:Option[String],
                     company:Option[String], turnover:Option[String], industries:Option[Seq[String]],
                     positions:Option[Seq[String]], cities:Option[Seq[String]], address:Option[String])

case class PasswordVo(mobile:String, password:String, newPassword:String)