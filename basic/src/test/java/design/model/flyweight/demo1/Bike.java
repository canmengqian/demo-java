package design.model.flyweight.demo1;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class Bike {
    Color color;



    /**
     * 轮子大小
     */
    double wheelSize;

    /**
     * 价钱
     */
    double price;

    String type;

    public Bike(double wheelSize, double price,ColorEnum color) {
        this.wheelSize = wheelSize;
        this.price = price;
        this.color = ColorManager.getColor (color);
    }

}
