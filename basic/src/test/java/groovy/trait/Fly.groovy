package groovy.trait

import groovy.util.logging.Slf4j

@Slf4j
trait Fly implements Behaviour, Action{
    def FLY="FLY"
    void fly() {
        println("fly")
    }
    void superFly() {
        println("superFly")
    }
}