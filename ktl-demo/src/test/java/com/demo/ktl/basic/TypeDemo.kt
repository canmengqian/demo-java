package com.demo.ktl.basic

import org.testng.annotations.Test
import java.io.Serializable
import kotlin.math.pow
import kotlin.random.Random


class TypeDemo : Serializable {


    val name = "这是字符串常量"
    var age: Int = 18 // 可变长整数
    var isMale = true  // 隐式设置类型

    @Test
    fun test_new(){
        val type = TypeDemo()

    }
    @Test
    fun str() {
        logger().error("www")
        val name = "tom"
        var str = "字符串模板 $name"
        logger().info(str)
        str.plus("字符串拼接")
        logger().info(str)


    }

    /**
     * 测试扩展方法和属性
     */
    @Test
    fun test_ext() {
        TypeDemo().desc()
        val s = TypeDemo().desc_1("带参数扩展")
        println(s)
    }

    /**
     * 测试kt对 java math的扩展
     */
    @Test
    fun test_math_ext() {
        // 原生写法
        var pow = Math.pow(2.0, 3.0)
        logger().info("2的3次方:${pow}")
        // kt写法
        pow = 2.0.pow(3.0)
        logger().info("2的3次方:${pow}")
    }

    /**测试条件表达式
     * 1. if表达式
     */
    @Test
    fun test_if() {
        var age = 18
        val str = if (age > 18) "成年人" else "未成年人"
        println(str)
        age = Random(1).nextInt(100)
        // 区间判断
        if (age in 18..35)
            println("${age}:打工仔")
        if (age in 36..65)
            println("${age}:退休工人")
        if (age !in 18..65)
            println("${age}:未知人群")
    }

    /**
     * 测试for循环
     */
    @Test
    fun test_for() {
        val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        for (i in list) {
            println(i)
        }
        for (i in list.indices) {
            println(i)
        }
        for (i in 0 until list.size) {
            println(i)
        }
    }

    /**
     * 区间类型
     *
     */
    fun range() {
        val range = 1..10
        range.forEach { println(it) }
    }

    @Test
    fun test_range() {
        range()
        // 不包含最后一个
        val range = 1 until 5
        range.forEach { println(it) }
        // 带有步长的区间
        val range_1 = 1..4 step 2
        range_1.forEach({ println("带有步长区间:${it}") })

    }

    /**
     * 测试数组
     */
    @Test
    fun test_array() {
        val arr_1 = intArrayOf(1, 2, 3)
        println(arr_1.toList().toString())
        val arr_2 = Array(5) { i -> i * i }
        println(arr_2.toList().toString())
    }

    /**
     * 可空类型
     */

    fun nullable(str: String?) {
        var str = str?.uppercase()
        println(str)
        str = str ?: "默认值"
        println(str)
    }

    @Test
    fun test_nullable() {
        val str: String? = null
        nullable(str)
    }

    /**
     * 类型检测
     */
    fun typeCheck(obj: Any) {
        if (obj is String) {
            println("str is String")
        } else if (obj is Int) {
            println("str is Int")
        } else {
            println("str is not String or Int")
        }
    }

    @Test
    fun test_typeCheck() {
        typeCheck("tom")
        typeCheck(1)
    }

    /**
     * 无类型的函数
     */
    fun hello(): Unit = println("Hello World")
    fun sum(a: Int, b: Int): Int {
        return a + b
    }

    /**
     * 方法简化写法
     */
    fun sum_1(a: Int, b: Int) = a + b

    fun 可变长参数(vararg args: Int) {
        for (arg in args) {
            println(arg)
        }
    }

    /**
     * 匿名函数
     */
    fun lambdaFunc() {
        val sum = { x: Int, y: Int -> x + y }
        println(sum(1, 2))
    }

}

/**
 * 简单的主方法,允许无参数
 */
/*
fun main() {
    println("Hello World")
}

fun main(args: Array<String>) {
   val demo =TypeDemo()
    println(demo.name)
    // 赋值无法通过编译
    //demo.name="更换了名字"
}*/
