package design.model.flyweight.demo1;

import java.util.LinkedHashMap;
import java.util.Map;

public class ColorManager {
    private  static  Map<ColorEnum,Color> colors = new LinkedHashMap<> ();

    static {
        colors.put (ColorEnum.RED,new Color ("红"));
        colors.put (ColorEnum.GREEN,new Color ("绿"));
        colors.put (ColorEnum.INDIGO,new Color ("青"));
        colors.put (ColorEnum.BLUE,new Color ("蓝"));
        colors.put (ColorEnum.VIOLET,new Color ("紫"));
        colors.put (ColorEnum.ORANGE,new Color ("橙"));
        colors.put (ColorEnum.YELLOW,new Color ("黄"));
        colors.put (ColorEnum.BLACK,new Color ("黑"));
    }

    public static Color getColor(ColorEnum type){
        if(colors.containsKey (type)){
            return  colors.get (type);
        }
       return null;

    }

}
