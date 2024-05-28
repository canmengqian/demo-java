package design.model.bridge.demo1.capacity;

public class LargeCapacity implements Capacity{
    @Override
    public long getCapacity() {
        return 1000;
    }
}
