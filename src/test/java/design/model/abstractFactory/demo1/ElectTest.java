package design.model.abstractFactory.demo1;

import design.model.abstractFactory.demo1.factory.AppleElectFactory;
import design.model.abstractFactory.demo1.factory.ElectFactory;
import design.model.abstractFactory.demo1.factory.HuaweiElectFactory;
import org.junit.jupiter.api.Test;

public class ElectTest {
    @Test
    void testCreatePhone(){
        // 生成手机
        ElectFactory electFactory = new HuaweiElectFactory ();
        System.out.println (electFactory.createPhone ().getBrand ());
        System.out.println (electFactory.creadPad ().getPadName ());

        electFactory = new AppleElectFactory ();
        System.out.println (electFactory.createPhone ().getBrand ());
        System.out.println (electFactory.creadPad ().getPadName ());
    }
}
