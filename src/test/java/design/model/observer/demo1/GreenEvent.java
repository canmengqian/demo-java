package design.model.observer.demo1;

public class GreenEvent implements Event{
    @Override
    public String eventName() {
        return "绿灯";
    }
}
