package groovy

import groovy.bean.GryUser
import groovy.transform.TypeChecked
import groovy.util.logging.Slf4j
import spock.lang.Specification

@Slf4j
@TypeChecked
class GryClosureTest extends Specification {
    def "闭包测试"() {
        given:
        def closure = {
            GryUser u ->
                log.info("name = ${u.name}, age = ${u.age}, sex = ${u.sex}")
        }
        and:
        def user = new GryUser(name: "zhangsan", age: 18)
        when:
        closure.call(user)
        closure(user)
        then:
        noExceptionThrown()
    }

    def "测试闭包带有参数,并指定参数带有默认值"() {
        given:
        def closure = {
            String name, int age, String sex = "man" ->
                log.info("name = ${name}, age = ${age}, sex = ${sex}")
                return sex
        }
        and:
        def user = new GryUser(name: "zhangsan", age: 18)
        when:
        def rs = closure.call(user.name, user.age)
        def rs1 = closure(user.name, user.age)
        then:
        verifyAll {
            rs == "man"
            rs1 == "man"
        }

    }

    def "测试闭包隐含参数"(){
        given:
        def greeting = {
                log.info("hello ${it}")
            return it
        }
        def user = "Tom"
        when:
        def rs = greeting(user)
        then:
        verifyAll {
            rs == "Tom"
        }
    }
    def "可变长参数闭包"(){
        given:
        def sum = {
            int... args ->
                log.info("args = ${args}")
                def sum = 0
                for (int i = 0; i < args.length; i++) {
                    sum += args[i]
                }
                return sum
        }
        when:
        def rs = sum(1,2,3,4,5)
        then:
        verifyAll {
            rs == 15
        }
    }
}
