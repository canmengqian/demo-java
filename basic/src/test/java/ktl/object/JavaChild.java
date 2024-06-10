package ktl.object;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JavaChild extends Person {
    @Override
    public void play() {
      log.info("我是Java类，玩玩具");
    }

    @Override
    public void humanTalking() {
        log.info ("我是Java类，不会说话");
    }

    @Override
    public void doSomething() {
        log.info("我是Java类，不会做事情");
    }
}
