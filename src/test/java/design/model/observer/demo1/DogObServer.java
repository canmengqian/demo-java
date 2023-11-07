package design.model.observer.demo1;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DogObServer implements RoadPassObserver{
    String name="小狗";
    @Override
    public void walk(Event event) {
      log.info ("{}观察到交通灯为{},可以行走",name,event.eventName ());
    }

    @Override
    public void prevent(Event event) {
        log.info ("{}观察到交通灯为{},禁止通行",name,event.eventName ());
    }
}
