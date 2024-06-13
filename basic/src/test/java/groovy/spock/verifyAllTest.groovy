package groovy.spock

import groovy.bean.GryUser
import spock.lang.Specification

class verifyAllTest extends Specification {
    def "验证多个值"() {
        expect:
        verifyAll {
            user_1.name == "zhangsan"
            user_2.name != "q"
            user_2.age == 10
            user_2.age != 20
        }
        where:
        user_1                     | user_2
        new GryUser(name: "zhangsan") | new GryUser(name: "q")
    }
}
