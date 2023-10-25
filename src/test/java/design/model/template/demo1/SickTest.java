package design.model.template.demo1;

import org.junit.jupiter.api.Test;

public class SickTest {
    @Test
    public  void  test(){
        //普通人问诊
        SickProcess normal = new NormalSickPerson ();
        normal.inquiry ();


        System.out.println ("模拟VIP 问诊流程");
        SickProcess vip = new VipSickPerson ();
        vip.inquiry ();
    }
}
