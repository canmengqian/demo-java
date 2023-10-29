package design.model.bridge.demo1;

import design.model.bridge.demo1.additive.Additive;
import design.model.bridge.demo1.capacity.Capacity;

/**
 * 咖啡制造机
 */
public class CoffeeMaker {

    private Additive additive;

    private Capacity capacity;


    public CoffeeMaker(Additive additive, Capacity capacity) {
        this.additive = additive;
        this.capacity = capacity;
    }

   public Coffee makeCoffee(String name){
        return  Coffee.builder ().additives (additive.getType ()).capacity (capacity.getCapacity ()).name (name).build ();
    }
}
