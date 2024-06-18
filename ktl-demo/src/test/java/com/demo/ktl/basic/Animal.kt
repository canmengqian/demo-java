package com.demo.ktl.basic


import org.jetbrains.kotlinx.dataframe.AnyFrame
import org.jetbrains.kotlinx.dataframe.api.dataFrameOf
import org.jetbrains.kotlinx.dataframe.api.forEach
import org.jetbrains.kotlinx.dataframe.api.head
import org.junit.jupiter.api.Test
import org.slf4j.Logger
import java.io.Serializable

class Animal : Serializable {
    /**
     * 类内部扩展函数
     */
    fun String.name() {

        println("${this}的name")
    }

    fun getNameByType(str: String): String {
        return when (str) {
            "cat" -> "小猫"
            "dog" -> "小狗"
            else -> "未知"
        }
    }

    @Test
    fun test_成员扩展函数() {
        val averageTemperature: AnyFrame = dataFrameOf(
            "city" to listOf("New York", "London", "Berlin", "Yerevan", "Tokyo"),
            "average temperature" to listOf(12.5, 11.0, 9.6, 11.5, 16.0)
        )
        averageTemperature.head(5).forEach {
            val i_1 = it[0]
            println(i_1)
        }

        "a".name()
    }

    //================================= 扩展函数/成员函数 引用测试 =================================

    @Test
    fun test_成员函数引用() {
        var num = (Int::toFloat)(1)
        println(num)
        num = Int::toFloat.invoke(1)
        println(num)
    }

    @Test
    fun test_扩展函数引用() {
        // 全局扩展函数可以被引用
        com.demo.ktl.basic.Animal::logger.invoke(this).info("hello")
    }


    @Test
    fun test_类内部成员函数引用赋值() {

        val v1: (com.demo.ktl.basic.Animal, String) -> String = com.demo.ktl.basic.Animal::getNameByType
        v1.invoke(this, "cat")
        val v2: com.demo.ktl.basic.Animal.(String) -> String = com.demo.ktl.basic.Animal::getNameByType
        v2.invoke(this, "cat")
        // 显示Receiver函数引用赋值
        val v3: (String) -> String = ::getNameByType
        v3.invoke("cat")
    }


    @Test
    fun test_全局扩展函数赋值() {
        // 显示Receiver函数引用赋值
        val v1: com.demo.ktl.basic.Animal.() -> Logger = com.demo.ktl.basic.Animal::logger
        v1.invoke(this).info("hello v1")
        // 隐式Receiver函数引用赋值
        val v2: (com.demo.ktl.basic.Animal) -> Logger = com.demo.ktl.basic.Animal::logger
        v2.invoke(this).info("hello v2")
        //TODO 这是什么写法
        val v3: () -> Logger = ::logger
        v3.invoke().info("hello v3")
        val v4 = v3
        v4.invoke().info("hello v4")

    }


}