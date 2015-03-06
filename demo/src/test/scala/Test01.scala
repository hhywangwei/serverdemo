package com.appeme.server.test

import org.scalatest.FunSuite

class Test01 extends FunSuite{

  test("Very Basic"){
    assert(1 == 1)
  }

  test("Another very basic"){
    assert("Hello world" == "Hello world")
  }

  test("Exception expected, does not fire, FAIL"){
    val msg = "hello"
    intercept[IndexOutOfBoundsException]{
      msg.charAt(0)
    }
  }

  test("Exception excepted, does not fire, SUCCESS"){
    val msg = "hello"
    intercept[IndexOutOfBoundsException]{
      msg.charAt(-1)
    }
  }

}
