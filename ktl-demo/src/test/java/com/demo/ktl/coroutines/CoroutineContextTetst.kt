package com.demo.ktl.coroutines

import kotlinx.coroutines.*
import kotlin.test.Test

class CoroutineContextTetst {
    /**
     * 获取当前上下文，构造一个新的上下文
     */
    @OptIn(ExperimentalCoroutinesApi::class, DelicateCoroutinesApi::class)
    @Test
    fun test_1() {
        GlobalScope.launch {
            val context = currentCoroutineContext()
            println("当前上下文 : ${context}")
            // 获取上下文元素
            val element = context[Job]
            println("元素 : ${element}")
            val context_1 = newCoroutineContext(Dispatchers.Unconfined)
            println("当前上下文_1 : ${context_1}")
            println("上下文是否变更: " + currentCoroutineContext())
        }
    }


    /**
     * 异常处理器
     */
    @Test
    fun test_2() {
        val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
            println("异常处理 : ${throwable}")
        }
        GlobalScope.launch(exceptionHandler + Dispatchers.Unconfined + CoroutineName("异常测试")) {
            val n = 1 / 0
        }
    }

    /**
     * 1.指定两个协程,其中一个引发异常，另一个不引发异常
     * 2. 不使用协程异常隔断job
     * 3. 不使用异常捕捉器
     * 结果: 其他协程因为异常无法执行
     */
    @Test
    fun test_3() {
        runBlocking {
            val launch_1 = launch {
                println("协程1执行,准备抛出异常")
                val n = 1 / 0
            }
            launch_1.join()
            val launch_2 = launch {
                println("协程2执行")
            }
            launch_2.join()
        }
    }

    /**
     * 引入supervisorJob,协程抛出异常后不影响其他协程
     * 结果:所有协程都执行
     */
    @Test
    fun test_4() {
        runBlocking {
            val launch_1 = launch(SupervisorJob()) {
                println("协程1执行,准备抛出异常")
                val n = 1 / 0
            }
            launch_1.join()
            val launch_2 = launch {
                println("协程2执行")
            }
        }
    }
}