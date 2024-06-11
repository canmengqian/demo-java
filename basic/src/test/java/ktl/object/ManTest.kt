package ktl.`object`

import org.junit.jupiter.api.Test

class ManTest {
    @Test
    fun  test_constructor(): Unit {
        val man = Man("zhangsan","Beijing")
        println(man.city)
    }
    @Test
    fun test_sayHello(): Unit {
        val man = Man()
        man.sayHello()
        println(man.city)
    }
}