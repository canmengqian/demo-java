package groovy.spock

import groovy.bean.SalaryManager
import groovy.bean.User
import groovy.util.logging.Slf4j
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Title
import spock.lang.Unroll

/**
 * 针对except的测试
 */
@Subject(ExceptTest)
@Title("测试异常")
@Slf4j
class ExceptTest extends Specification {
    @Shared
    User user = new User(name: "zhangsan");

    def "单一 given-when-then模式"() {
        given: "简单的bool值,此处为文档注释"
        def a = true
        when: "判断真假"
        def rs = a.is(true)
        then: "判断结果"
        rs
    }

    def "test 两个判断条件"() {
        given: "简单的bool值,此处为文档注释"
        def a = true
        def b = false
        when: "判断真假"
        def rs = a.is(true)
        then: "判断结果"
        rs
        and:
        when:
        def rs1 = b.is(true)
        then:
        rs1
    }

    def "使用expect替换given-when-then模式"() {
        expect:
        def a = true
        def b = false
        a || b == true
    }

    def "where 模式:批量数据进行测试"() {
        expect:
        c == (a + b)
        where: "批量数据进行测试"
        a  | b | c
        1  | 2 | 3
        2  | 3 | 5
        10 | 2 | 12
    }

    def "模拟发放薪资测试 "() {
        SalaryManager salaryManager = Mock(SalaryManager);
        salaryManager.giveSalary(user) >> 20
        salaryManager.giveSalary(_) >> 30
        expect:
        verifyAll {
            salaryManager.giveSalary(user) == 20
            salaryManager.giveSalary(new User(name: "lisi")) == 30
        }
    }

    @Unroll
    def "验证多个值"() {
        expect:
        with() {
            user_1.name == "zhangsan"
            user_2.name == "q"
        }
        where:
        user_1                      | user_2
        new User(name: "zhangsan")  | new User(name: "q")
        new User(name: "zhangsan1") | new User(name: "lisi")
    }

    def setup() {
        log.info("每个方法都执行一次-前置方法")
    }

    def cleanup() {
        log.info("每个方法都执行一次-后置方法")
    }

    def setupSpec() {
        log.info("测试类执行一次-前置方法")
    }

    def cleanupSpec() {
        log.info("测试类执行一次-后置方法")
    }


}
