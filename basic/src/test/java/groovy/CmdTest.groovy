package groovy

import spock.lang.Specification

class CmdTest extends Specification{
    def "执行命令行"() {
        given:
        def cmd = "ipconfig".execute()
        expect:
        cmd.each { println it}
    }
}
