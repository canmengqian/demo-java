package design.model.flyweight.demo1;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class BikeTest {
    @Test
    public  void testBike(){
        Bike phoenixRed = new Bike (26,100,ColorEnum.RED);
        phoenixRed.setType ("凤凰牌");
        System.out.println (phoenixRed.toString ());
        Bike phoenixBlack = new Bike (27,100,ColorEnum.BLACK);
        phoenixBlack.setType ("凤凰牌");
        System.out.println (phoenixBlack.toString ());
        Bike jatxBlack = new Bike (25,100,ColorEnum.BLACK);
        jatxBlack.setType ("捷安特");
        System.out.println (jatxBlack.toString ());

        log.info ("颜色相等吗{}",phoenixBlack.getColor ().equals (jatxBlack.getColor ()));
    }

}
