package design.model.observer.demo1;

import org.junit.jupiter.api.Test;

public class ObserverTest {
    @Test
    public void testLight(){
        RoadPassObserver car1 = new CarObServer ();
        RoadPassObserver dog1 = new DogObServer ();
        TrafficLight light = new TrafficLight ();
        light.addObserver (car1);
        light.addObserver (dog1);

        light.change ();

        RoadPassObserver car2 = new CarObServer ();
        RoadPassObserver dog2 = new DogObServer ();

        light.addObserver (car2);
        light.addObserver (dog2);
        light.change ();

        RoadPassObserver car3 = new CarObServer ();
        RoadPassObserver dog3 = new DogObServer ();

        light.addObserver (car3);
        light.addObserver (dog3);
        light.change ();


    }
}
