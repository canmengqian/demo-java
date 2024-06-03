package groovy

import groovy.bean.User
import groovy.util.logging.Slf4j
import spock.lang.Specification

@Slf4j
class ClosureTest extends Specification {
    def "闭包测试"() {
        given:
        def closure = {
            User u ->
                log.info("name = ${u.name}, age = ${u.age}, sex = ${u.sex}")
        }
        and:
        def user = new User(name: "zhangsan", age: 18)
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
        def user = new User(name: "zhangsan", age: 18)
        when:
        def rs = closure.call(user.name, user.age)
        def rs1 = closure(user.name, user.age)
        then:
        verifyAll {
            rs == "man"
            rs1 == "man"
        }

    }
}
