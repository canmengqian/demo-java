package design.model.observer.demo1;

public interface RoadPassObserver {
    void walk(Event event);

    void  prevent(Event event);
}
