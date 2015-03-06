package com.appeme.server.test

import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalamock.scalatest.MockFactory

trait Converter {
  def convert(amount:BigDecimal, from:String, tag:String): BigDecimal
}

class MoneyService(converter:Converter){
  def sendMoneyToSweden(amount:BigDecimal,from:String): BigDecimal = {
    val convertedAmount = converter.convert(amount,from,"SEK")
    println(s" $convertedAmount SEK are on their way...")
    convertedAmount
  }
  def sendMoneyToSwedenViaEngland(amount:BigDecimal,from:String):  BigDecimal = {
    val englishAmount = converter.convert(amount,from,"GBP")
    println(s" $englishAmount GBP are on their way...")
    val swedishAmount = converter.convert(amount,"GBP","SEK")
    println(s" $swedishAmount SEK are on their way...")
    swedishAmount
  }
}

@RunWith(classOf[JUnitRunner])
class Test08 extends FlatSpec with MockFactory with Matchers{
  "Sending money to sweden" should "convert into SEK" in {
    val converter = mock[Converter]
    val moneyService = new MoneyService(converter)

    (converter.convert _ ).expects(BigDecimal("200"),"EUR","SEK").returning(BigDecimal(1750))

    val amount = 200
    val from = "EUR"
    val result = moneyService.sendMoneyToSweden(amount, from)
    result.toInt should be (1750)
  }
}
