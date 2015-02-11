package com.appeme.server.demo.user.repositories

import akka.actor.Props
import com.mongodb.casbah.Imports._
import org.joda.time.format.DateTimeFormat
import akka.actor.{Actor, ActorLogging}
import com.mongodb.casbah.MongoClient
import com.appeme.server.demo.user.vo._
import com.appeme.server.demo.user.domain._
/**
 * 用户数据操作
 *
 * @author WangWei
 */

object UserRepositories{

  def props(mongoClient: MongoClient, database:String) = Props(new UserRepositories(mongoClient, database))
}

class UserRepositories(mongoClient:MongoClient, database:String) extends Actor with ActorLogging{
  val collection = mongoClient.getDB(database).getCollection("ser_user")

  override def preStart() = {
    log.debug("Starting user repositories......")
  }

  def insert(m:User) ={
    collection.save(m)
    m
  }

  def update(vo:UpdateVo) = {
    collection.update(MongoDBObject("id" -> vo.id),
      MongoDBObject("$set" -> MongoDBObject(
                    "name" -> vo.name,
                    "icon" -> vo.icon,
                    "company" -> MongoDBObject(
                        "name" -> vo.company,
                        "industries" -> vo.industries,
                        "turnover" -> vo.turnover
                    ),
                    "Job" -> MongoDBObject(
                        "positions" -> vo.positions
                    ),
                    "cities" -> vo.cities,
                    "address" -> vo.address)))
    val o = collection.findOne(MongoDBObject("id" -> vo.id))
    sender() ! o.toString()
  }

  def receive = {
    case vo:RegisterVo =>
      insert(User(vo.mobile, vo.name, vo.password))
      sender() ! "{\"ok\":true}"
    case vo:UpdateVo =>
      update(vo)
    case _ =>
      log.debug("operator not register")
  }
}