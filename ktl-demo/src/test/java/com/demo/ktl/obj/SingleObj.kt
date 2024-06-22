package com.demo.ktl.obj

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.junit.jupiter.api.Test

object SingleObj {
     val name: String = "zhangsan"
     val age: Int = 18

    @Test
    fun test(){
        val s = SingleObj
        println(ObjectMapper().registerKotlinModule().writeValueAsString(s))
    }
}
