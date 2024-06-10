package groovy

import groovy.util.logging.Slf4j
import spock.lang.Specification

import java.nio.charset.Charset

@Slf4j
class IOTest extends Specification {
    def "读取测试资源路径下的txt文件"() {
        given:
        def file = new File("src/test/resources/test.txt")
        when:
        file.eachLine {log.info("当前行的内容:${it}")}
        def content = file.text
        then:
        log.info("content = ${content}")
        noExceptionThrown()
    }
    def "使用Reader来读取文件"() {
        given:
        def file = new File("src/test/resources/test.txt")
        when:
       file.withReader(Charset.defaultCharset().name(),{it.eachLine {log.info("当前行的内容:${it}")}})
        then:
        noExceptionThrown()
    }
}
