package com.appeme.server.test

import java.util.Stack
import org.scalatest.{FeatureSpec,GivenWhenThen}

class Test06 extends FeatureSpec with GivenWhenThen{

  feature("The user can pop an element off the top of the stack"){
    info("As a programmer")
    info("I want to be able to pop item off the stack")
    info("So thant I can get them in last-in-first-out order")

    scenario("pop is invoked on a non-empty stack"){
      Given("a non-empty stack")
      val stack =new Stack[Int]
      stack.push(1)
      stack.push(2)
      val oSize = stack.size()
      When("堆栈(stack)调用pop方法")
      val result = stack.pop()
      Then("得到最后压入堆栈数字")
      assert(result == 2)
      And("堆栈减少一个元素")
      assert(stack.size() == (oSize -1))
    }
  }
}
