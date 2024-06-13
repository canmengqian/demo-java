package ktl.coroutines

import kotlinx.coroutines.*
import org.junit.jupiter.api.Test

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
}