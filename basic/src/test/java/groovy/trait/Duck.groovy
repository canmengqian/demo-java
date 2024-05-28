package groovy.trait

import groovy.util.logging.Slf4j

@Slf4j
class Duck implements Fly{
    @Override
    void fly() {
      log.info("不能飞翔,可以游泳")
    }
}
