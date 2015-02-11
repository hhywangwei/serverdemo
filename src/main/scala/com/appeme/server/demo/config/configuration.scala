package com.appeme.server.demo.config

import akka.actor.ActorSystem
import com.mongodb.{MongoCredential,ServerAddress}
import com.mongodb.casbah.MongoClient
import com.mongodb.casbah.Imports._

/**
 * Mongo DB数据库配置
 *
 * @author WangWei
 */
trait MongoDataSource {
  def mongoClient: MongoClient
}

trait MongoConfigure extends MongoDataSource{
  def system:ActorSystem

  private def config = system.settings.config
  private val mongoConfig = config.getConfig("db.mongo")
  private val host = mongoConfig.getString("host")
  private val port = mongoConfig.getInt("port")
  private val defaultDB = mongoConfig.getString("defaultDB")
  private val user = mongoConfig.getString("user")
  private val password = mongoConfig.getString("password")

  val serverAddress = new ServerAddress(host, port)
  val credentials = MongoCredential.createMongoCRCredential(user, defaultDB, password.toCharArray)

  lazy val mongoClient = MongoClient(serverAddress, List(credentials))
}
