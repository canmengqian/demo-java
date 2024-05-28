package groovy.basic

import groovy.util.logging.Slf4j
import org.junit.jupiter.api.Test

@Slf4j
class FunctionParameterTest {
    /*默认参数*/

    private static int DEFAULT_AGE = 20

    /**
     * 方法默认参数
     * @param name
     * @param age
     */
    static void func_1(String name = "zhangsan", int age = DEFAULT_AGE) {
        log.info("name = ${name}, age = ${age}")
    }

    /**
     * 无需return关键字
     * @param name
     * @param age
     * @return
     */
    static String func_2(String name = "zhangsan", int age = DEFAULT_AGE) {
        name.concat(age as String)
    }

    /*默认参数*/

    @Test
    void test_func_1() {
        func_1()
        func_1("lisi")
        func_1("lisi", 18)
    }

    @Test
    void test_func_2() {
        def str = func_2()
        log.info("str = ${str}")
        str = func_2("lisi")
        log.info("str = ${str}")
        str = func_2("lisi", 18)
        log.info("str = ${str}")
    }

}
