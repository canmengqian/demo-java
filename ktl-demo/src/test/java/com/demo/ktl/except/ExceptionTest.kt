package com.demo.ktl.except

import kotlin.test.Test


class ExceptionTest {
    @Test
    fun test_exception() {
        val a = 1 / 0
        try {
            val a = 1 / 0
        } catch (e: Exception) {
            println(e.message)
        }
    }

    /**
     * 测试异常处理
     */
    @Test
    fun test_exception_1() {
        val num = kotlin.runCatching { 1 / 0 }
            .onFailure { println(it.message) }
            .onSuccess { println(it) }
            .getOrNull() ?: 1
        println(num)
    }
}