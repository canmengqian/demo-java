package ktl.obj

import org.junit.jupiter.api.Test

class HumanTest {
    /**
     * 测试实现接口相关的方法
     */
    @Test
    fun test_constructor(): Unit {
        val man = Man("zhangsan","Beijing")
        man.humanTalking()
        man.doSomething()

        val  woman = Woman()
        woman.humanTalking()
        woman.doSomething()

        val child = JavaChild()
        child.humanTalking()
        child.doSomething()
    }
}