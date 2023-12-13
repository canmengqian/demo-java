package design.model.observer.demo1;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class TrafficLight {
    List<RoadPassObserver> observerList = new ArrayList<> ();

    Event lastEvent ;

    public  void  addObserver(RoadPassObserver observer){
        observerList.add (observer);
    }


    public void change(){
        if(lastEvent ==null){
            lastEvent = new GreenEvent ();
            log.info ("当前交通灯是{}",lastEvent.eventName ());
            observerList.forEach (o->o.walk (lastEvent));
            Event redEvent = new RedEvent ();
            log.info ("红绿灯将由{}变{}",lastEvent.eventName (),redEvent.eventName ());
            lastEvent = redEvent;
            observerList.clear ();
            return;
        }
        if(lastEvent.getClass ().equals (RedEvent.class)){
            log.info ("当前交通灯是{}",lastEvent.eventName ());
            observerList.forEach (o->o.prevent (lastEvent));
            Event greenEvent = new GreenEvent ();
           // observerList.clear ();
            log.info ("红绿灯将由{}变{}",lastEvent.eventName (),greenEvent.eventName ());
            lastEvent = greenEvent;
            return;
        } else if (lastEvent.getClass ().equals (GreenEvent.class)) {
            log.info ("当前交通灯是{}",lastEvent.eventName ());
            observerList.forEach (o->o.walk (lastEvent));
            Event redEvent = new RedEvent ();
            log.info ("红绿灯将由{}变{}",lastEvent.eventName (),redEvent.eventName ());
            lastEvent = redEvent;
           observerList.clear ();
            return;
        }

    }


    public  void change(Event event){
        observerList.forEach (o-> o.walk (event));
    }

}
