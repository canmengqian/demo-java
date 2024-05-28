package design.model.facede.demo1;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 服务员,充当门面
 */
@Slf4j
public class FacedeServer {
    private static final  Chef chef = new Chef ();
    private static final  Cashier cashier = new Cashier ();
    private static final  Dishwasher dishwasher = new Dishwasher ();

    public static List<String> cooking(){
        log.info ("我会炒菜");
      return chef.cooking ();
    }

    public static void wash(){
        log.info ("我会洗碗");
        dishwasher.wash ();
    }

    public static void settle(double cash){
        log.info ("我会算账");
        cashier.settle (cash);
    }

    public static   void  introduce(){
        log.info ("我是丰泽园的大堂经理,我什么事都会做");
    }


}
