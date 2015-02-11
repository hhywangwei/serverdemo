package com.appeme.server.demo.user.domain

class Job (val positions:Seq[String])

object Job {
  def apply() = {
    new Job(Seq[String]())
  }

  implicit def apply(positions:Seq[String]) ={
    new Job(positions)
  }
}

class Company(val name:String, val industries:Seq[String], val turnover:String)

object Company{
  def apply() = {
    new Company("",Seq[String](),"")
  }

  def apply(name:String, industries:Seq[String], turnover:String) ={
    new Company(name, industries, turnover)
  }
}

class User(val id:String, val loginName:String, val mobile:String, val name:String, val password:String,
           val icon:String, val company: Company, val job: Job, val cities:Seq[String], val address:String,
           val status:String, val createTime:String, val createTimeL:Long)

object User{
  import java.util.UUID._
  import org.joda.time.DateTime
  import org.joda.time.format.DateTimeFormat
  import com.mongodb.casbah.Imports._

  val emptyStr = ""

  def formatTime(timeL:Long) = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").print(timeL)

  def apply(mobile:String, name:String , password:String) = {
    val id = randomUUID().toString
    val status = "NEW"
    val timeL = DateTime.now().getMillis;
    new User(id, mobile, mobile, name, password, emptyStr,
      Company(), Job(), Seq[String](), emptyStr, status, formatTime(timeL), timeL)
  }

  implicit def convertMongoObject(u: User) = {
    MongoDBObject("_id" -> u.id,
                  "id" -> u.id,
                  "loginName" -> u.loginName,
                  "mobile" -> u.mobile,
                  "name" -> u.name,
                  "password" -> u.password,
                  "icon" -> u.icon,
                  "company" -> MongoDBObject(
                      "name" -> u.company.name,
                      "industries" -> u.company.industries,
                      "turnover" -> u.company.turnover
                  ),
                  "Job" -> MongoDBObject(
                       "positions" -> u.job.positions
                  ),
                  "cities" -> u.cities,
                  "address" -> u.address,
                  "status" -> u.status,
                  "createTime" -> u.createTime,
                  "createTimeL" -> u.createTimeL)
  }
}