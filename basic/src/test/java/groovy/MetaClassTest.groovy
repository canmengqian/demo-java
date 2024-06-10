package groovy

import spock.lang.Specification

class MetaClassTest extends Specification{
    def "元编程测试"(){
        given: "添加hello方法"
        String.metaClass.hello = {
            println "hello $it"
        }
        def str ="he"
        when:
        str.hello()
        then:
        noExceptionThrown()
    }
}
