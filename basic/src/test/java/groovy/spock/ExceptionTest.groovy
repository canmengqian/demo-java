package groovy.spock

import spock.lang.Specification
import spock.lang.Title

@Title("测试异常")
class ExceptionTest extends Specification {
    def "测试异常"() {
        when:
        def a = 1 / 0
        then:
        def err=thrown(ArithmeticException)
        err.getMessage()
    }
    def "不抛出异常"() {
        when:
        def a = 1 / 1
        then:
        noExceptionThrown()
    }
}
