package com.demo.ktl.obj

import org.testng.annotations.Test

class PersonTest {

    /**
     * 测试抽象类的抽象方法
     */
    @Test
    fun test_constructor() {
        val man = Man("zhangsan", "Beijing")
        man.play()
        val woman = Woman()
        woman.play()
        val person = JavaChild()
        person.play()
    }


}