package groovy.trait

import spock.lang.Specification

class Bird extends Specification implements Fly,SuperFly{
    def behaviour() {
       println "bird "+FLY
    }

    // 覆盖Action中的action方法
    @Override
    Object action() {
        return "EAT , FLY ,SLEEP"
    }

    def "测试超类常量"(){
        given:
        def bird = new Bird()
        when:
        bird.behaviour()
        then:
        noExceptionThrown()
    }

    def "测试action"(){
        given:
        def bird = new Bird()
        when:
       def rs= bird.action()
        then:
        rs=="EAT , FLY ,SLEEP"
    }


}
