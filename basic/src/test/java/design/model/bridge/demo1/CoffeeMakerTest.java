package design.model.bridge.demo1;

import design.model.bridge.demo1.capacity.LargeCapacity;
import design.model.bridge.demo1.capacity.SmallCapacity;
import design.model.bridge.demo1.additive.MilkAdditive;
import design.model.bridge.demo1.additive.SugerAdditive;
import org.junit.jupiter.api.Test;

public class CoffeeMakerTest {
    @Test
    public  void  testMakeCoffee(){
        CoffeeMaker maker = new CoffeeMaker (new SugerAdditive (),new LargeCapacity());
        System.out.println (maker.makeCoffee ("瑞信"));

         maker = new CoffeeMaker (new MilkAdditive (),new SmallCapacity());
        System.out.println (maker.makeCoffee ("星巴克"));
    }
}
