package com.demo.ktl.set

import org.testng.annotations.Test

class ListTest {
    @Test
    fun test_list() {
        // 不可变
        var list = listOf(1, 2, 3, 4, 5)
        // 获取元素
        println("下标获取 ${list[0]}")
        println("get()获取".plus(list.get(0)))
        // 获取第一个元素
        list.first()
        // 获取最后一个元素
        list.last()
        list.last { it > 2 }
        list.plus(1)
        list.plus(1)
        list.plus(1)
        println(list)
        list = kotlin.runCatching { list.dropLast(1) }
                .onFailure { it -> println(it.message) }
                .onSuccess { println("添加元素成功") }
                .getOrNull() ?: listOf()
        println(list)

        val mutableList = mutableListOf(1, 2, 3, 4, 5)
        mutableList.add(6)
        println(mutableList)
    }
}