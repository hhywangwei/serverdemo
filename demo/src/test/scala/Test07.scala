package com.appeme.server.test

import org.scalatest.{FlatSpec,Matchers}
class Test07 extends FlatSpec with Matchers{
  "This test" should "pass" in {
    true should be === true
  }

  "This test" should "fail" in{
    true should be === false
  }
}
