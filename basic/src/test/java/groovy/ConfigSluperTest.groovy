package groovy

import spock.lang.Specification

class ConfigSluperTest extends Specification {
    def "test config sluper"() {
        given:
        def config = new ConfigSlurper().parse('''
        foo = "bar"
        bar = "foo"
''')
        expect:
        config.foo == "bar"
        config.bar == "foo"
    }
}
