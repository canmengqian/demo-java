package design.model.observer.demo1;

public class RedEvent implements  Event{
    @Override
    public String eventName() {
        return "红灯";
    }
}
