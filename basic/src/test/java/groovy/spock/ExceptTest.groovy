package groovy.spock

import groovy.bean.GryUser
import groovy.bean.SalaryManager
import groovy.util.logging.Slf4j
import spock.lang.*

/**
 * 针对except的测试
 */
@Subject(ExceptTest)
@Title("测试异常")
@Slf4j
class ExceptTest extends Specification {
    @Shared
    GryUser user = new GryUser(name: "zhangsan")

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
            salaryManager.giveSalary(new GryUser(name: "lisi")) == 30
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
        user_1                         | user_2
        new GryUser(name: "zhangsan")  | new GryUser(name: "q")
        new GryUser(name: "zhangsan1") | new GryUser(name: "lisi")
    }


    def "异常断言"() {
        when:
        getUser(user)
        then:

        def e = thrown(exception)
        log.info(e.getClass().getName());

        where:
        user                          | exception
        new GryUser(name: "zhangsan") | IllegalArgumentException
        new GryUser(name: "lisi")     | NullPointerException
    }

    def getUser(GryUser user) {
        if (user.name.equals("zhangsan")) {
            throw new IllegalArgumentException()
        }
        if (user.name.equals("lisi")) {
            throw new NullPointerException()
        }
    }

    @Unroll
    def "validate extension of #fileToValidate"() {
        when: "validator checks filename"
        def isValid = validate fileToValidate

        then: "return appropriate result"
        isValid == expectedResult

        where: "input files are"
        fileToValidate || expectedResult
        'some.jpeg'    || true
        'some.jpg'     || true
        'some.tiff'    || false
        'some.bmp'     || true
        'some.png'     || false
    }

    def static fileToValidate(String file) {
        return file.endsWith('jpeg') || file.endsWith('jpg') || file.endsWith('bmp')
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
