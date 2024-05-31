package groovy.spock

import groovy.bean.User
import spock.lang.Specification

class WithTest extends Specification {
    def "test with"() {
        given:
        def user = new User(name: "zhangsan", age: 18)
        expect:
        with(user) {
            name == "zhangsan"
            age == 18
        }
    }

    def "第一个结果失败，后续验证不再执行"() {
        given:
        def user = new User(name: "zhangsan", age: 18)
        expect:
        with(user) {
            name == "lisi"
            age == 18
        }
    }

    def "软断言不中断测试"() {
        given:
        def user = new User(name: "zhangsan", age: 18, sex: "man")
        expect:
        verifyAll {
            user.sex == "wman"
            user.name == "zhanwgsan"
            user.age == 18
        }
    }
}
