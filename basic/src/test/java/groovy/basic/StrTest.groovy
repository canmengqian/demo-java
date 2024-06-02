package groovy.basic

import groovy.bean.User
import groovy.util.logging.Slf4j
import org.junit.jupiter.api.Test
import spock.lang.Specification

@Slf4j
class StrTest extends Specification {
    @Test
    void testStr_1() {
        /*不进行插值：单引号,单引号组
        * 进行插值: 双引号,双引号组
        * */
        def str = 'hello world!'
        log.info("str = ${str}")
        def str2 = 'hello world! ${1+1}'
        log.info("str2 = ${str2}")
        str = '''hello world! ${1+1}'''
        log.info("str = ${str}")
        def str3 = '''hello world!
        ${1+1}
        自动换行
        '''
        log.info("str3 = ${str3}")

        /*双引号*/
        def str4 = "hello world! ${1 + 1}"
        log.info("表达式计算 str4 = ${str4}")
        def name = "zhangsan"
        def str5 = "hello ${name}"
        log.info("变量占位 str5 = ${str5}")

        /*对象访问*/
        def user = new User(name: "zhangsan", age: 18)
        def str6 = "name = $user.name, age = $user.age"
        log.info("对象访问 str6 = ${str6}")

        /*闭包访问*/
        def str7 = "简单加法 ${-> 1 + 1}"
        log.info("闭包访问 str7 = ${str7}")
        def str8 = "闭包访问 ${-> { def a = 1; def b = 2; return a + b }}"
        log.info("闭包访问,多语句闭包 str8 = ${str8}")
        /*带参数闭包*/
        // TODO
        /*   def str9 = "带参数闭包 ${a, b ->
               {
                   return a + b
               }
           }"
           log.info("带参数闭包 str9 = ${str9}")*/
        /*双重引号组*/
        def str10 = """
        hello world!
        ${1 + 1}
        自动换行
        """
        log.info("双重引号组 str10 = ${str10}")
    }

    /*字符测试*/

    @Test
    void testStr_2() {
        /*字符转换*/
        def ch = 'a'
        assert ch == 'a'
        ch = 'b' as char
        assert ch == (char) 'b'

    }

    @Test
    void testStr_3() {

        T tom = new T(name: null, age: 18)
        log.info("tom = ${tom}")
        // null 值处理
        def name = tom.name ?: "zhangsan"
        log.info("name is ${name}")

    }

    class T {
        String name
        int age;
    }

    def "闭包重新计算"(){
        given:
        def name ="zhangsan"
        when:
        def str = "hello ${-> name}"
        log.info("str = ${str}")
        and: "重新赋值"
        name = "lisi"
        then:
        log.info("str = ${str}")
        verifyAll {
            str == "hello lisi"
            str == "hello zhangsan"
        }
    }
    def "GString 和String hashCode 比较"(){
        given:
        def str = "hello zhangsan"
        def name ="zhangsan"
        def gString = "hello ${name}"
        when:
        def bool = str.hashCode() == gString.hashCode()
        then:
        log.info("bool = ${bool}")
        bool == true
    }
}
