package com.appeme.server.test

import org.scalacheck._
import Arbitrary._
import Prop.forAll

object ConverterSpecification extends Properties("Converter") with Converter{

  def convert(amount:BigDecimal, from:String, tag:String) = amount

  val currencies = Gen.oneOf("EUR","GBP","SEK","JPY")

  lazy val conversions:Gen[(BigDecimal, String, String)] = for {
    amt <- arbitrary[Int] suchThat {_ >= 0}
    from <- currencies
    to <- currencies
  } yield (BigDecimal(amt), from, to)

  property("Conversion to same value") = forAll(currencies){ c:String =>
      val amount = BigDecimal(200)
      val convertedAmount = convert(amount, c, c)
      convertedAmount == amount
  }

  property("Various currencies") = forAll(conversions){ c =>
      val convertedAmount = convert(c._1, c._2, c._3)
      convertedAmount >= 0
  }
}