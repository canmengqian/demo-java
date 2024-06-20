package com.demo.ktl.iline

import kotlin.test.Test

class InLineTest {
    /**
     * 不做内联处理
     */
    fun hello(block: () -> Unit) {
        println("hello")
        block()
        println("over")
    }

    /**
     * 内联处理
     */
    inline fun hello_1(block: () -> Unit) {
        println("hello")
        block()
        println("over")
    }

    /**
     * 内联，但允许返回函数参数本身
     */
    inline fun hello_2(noinline block: () -> Unit): () -> Unit {
        println("hello")
        return block
    }

    @Test
    fun test_1() {
        hello { println("world") }
        hello_1 { println("world") }
    }

    @Test
    fun test_参数带返回块() {
         hello { println("world");return@hello }
        hello_1 { println("world");return }
        hello_1 { println("world");return@hello_1 }
        val f = hello_2 { println("world");return@hello_2 }
        f()
    }
}