package ktl.basic

import org.junit.jupiter.api.Test
import java.io.Serializable

open class Person : Biology, Serializable {

    open fun walk():Unit{
        println("person walk")
    }
    /**
     * 成员扩展函数
     */
    fun String.name(): String {
        return "I am Person"
    }

    @Test
    fun test_成员扩展函数() {
        // 作用域只限于当前类下的 字符串【Receiver】
        "1".name()
    }

    @Test
    fun test_simple() {
        logger().info("test_simple")
    }

    @Test
    fun test_abs() {
        var n = abs(-1.0)
        println(n)
    }


}