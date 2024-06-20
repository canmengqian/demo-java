package com.demo.ktl.coroutines


import kotlinx.coroutines.*

import org.testng.annotations.Test

/**
 * 协程测试
 */
class CoroutinesTest {
    fun name() {
        println("hello world")
    }

    @Test
    fun test_coroutines() {
        runBlocking {
            launch {
                println("launch:${Thread.currentThread().name}")
                name()
            }
        }
        println("hello")
    }

    /**
     * 测试协程的并发
     */
    @Test
    fun test_coroutines_1() {
        runBlocking(context = Dispatchers.Default, {

            repeat(10000) {
                launch {
                    println("launch:${Thread.currentThread().name}")
                    delay(1000)
                    println("word")
                }
            }
        })
        runBlocking {
            repeat(10000) {
                launch {
                    println("launch:${Thread.currentThread().name}")
                    delay(1000)
                    println("word")
                }
            }
        }


    }

    @Test
    fun test_coroutines_2() {
        GlobalScope.launch(Dispatchers.Default) {
            delay(1000)
            println(Thread.currentThread().name)
        }
        Thread.currentThread().join(2000)
    }

    /**
     * 协程的job句柄
     */
    @Test
    fun test_coroutines_3() {
        runBlocking(Dispatchers.Default) {
            val job = launch {

                repeat(10) { i ->
                    println("job: I'm sleeping $i ...")
                    delay(500)
                }
            }
            job.start()
            println("job is active: ${job.isActive}")
            println("job is completed: ${job.isCompleted}")
            println("job is cancelled: ${job.isCancelled}")
            job.invokeOnCompletion { println("job is completed") }

        }
        Thread.currentThread().join(2000)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun test_coroutines_4() {
        runBlocking(Dispatchers.Default) {
            // 挂起函数,协程1
            val job = launch(Dispatchers.Default, CoroutineStart.LAZY) {
                repeat(1) {
                    println(this.coroutineContext[Job])
                }
            }
            // 挂起函数,协程2
            val job_1 = launch(Dispatchers.IO, CoroutineStart.LAZY) {
                repeat(1) {
                    println(this.coroutineContext[Job])
                }
            }
            println("job $job is start ${job.isActive}")
            println("job_1 $job_1 is start ${job_1.isActive}")
            val job_2 = this.coroutineContext[Job]
            println("最外部的job $job_2")
            println("最外部的job 是否启动" + job_2?.isActive)
            delay(100)
            job_2?.start()
            job.start()
            job_1.start()
            println("job $job is start ${job.isActive},父类协程为:${job.parent}")
            println("job_1 $job_1 is start ${job_1.isActive},父类协程为:${job.parent}")

        }
    }

    @Test
    fun test_协程分配器() {
        runBlocking(Dispatchers.Default) {
            // 默认线程分配器
            val c_1 = launch(Dispatchers.Default, CoroutineStart.LAZY) {
                println("c-1 ${Thread.currentThread().name}")
            }
            // io 线程分配器
            val c_2 = launch(Dispatchers.IO, CoroutineStart.LAZY) {
                println("c-2 ${Thread.currentThread().name}")
            }
            // 主线程分配
            /* Main线程分配器需要安卓平台
           val c_3 = launch(Dispatchers.Main, CoroutineStart.LAZY) {
               println("c-3 ${Thread.currentThread().name}")
           }

            val c_4 = launch(Dispatchers.Main.immediate, CoroutineStart.LAZY) {
                println("c-4 ${Thread.currentThread().name}")
            }*/
            val c_5 = launch(newSingleThreadContext("myThread - 专用单一线程"), CoroutineStart.LAZY) {
                println("c-5 ${Thread.currentThread().name}")
            }
            val c_6 = launch(newFixedThreadPoolContext(10, "myThread - 专用线程池"), CoroutineStart.LAZY) {
                println("c-6 ${Thread.currentThread().name}")
            }
            val c_7 = launch(Dispatchers.Unconfined, CoroutineStart.LAZY) {
                println("c-6 ${Thread.currentThread().name}")
            }
            c_1.start()
            c_2.start()
            /*  c_3.start()
                 c_4.start()*/
            c_5.start()
            c_6.start()
            c_7.start()
        }
    }

    @Test
    fun test_协程上下文() {
        var job: Boolean? = null
        suspend {
            job = withContext(Dispatchers.Default) {
                println("hello")
                val job = launch(Dispatchers.Default, CoroutineStart.LAZY) {
                    repeat(1) {
                        println(this.coroutineContext[Job])
                    }
                }
                job.start()
            }
        }
        println(job)
        Thread.currentThread().join(1000)

    }

    @Test
    fun test_协程超时异常() {
        // 指定时间内完成
        runBlocking {
            withTimeout(1000) {
                launch {
                    println("hello")
                }
            }
        }
        // 执行超时
        runBlocking {
            withTimeout(1000) {
                launch {
                    delay(2000)
                    println("hello")
                }
            }
        }
    }

    @Test
    fun test_异常处理器(){
        runBlocking {
            val handler = CoroutineExceptionHandler { _, throwable ->
                println("exception:${throwable.message}")
            }
            launch { println("hello") }
        }
    }


}