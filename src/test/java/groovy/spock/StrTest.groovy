package groovy.spock

import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Title

@Title("测试字符串")
@Subject(StrTest)
class StrTest extends Specification {
    def "测试字符串"() {
        given:
        def str = "hello world!"
        expect:
        str == "hello world!"
    }

    def "测试字符串2"() {
        expect:
        str == "hello world!"
        where:
        str << ["hello world!", "1", "2"]
    }

    def "test str length"() {

        expect:
        str.length() == length
        where:
        str | length
        "a" | 5+1
        "b" | 2
    }
}
