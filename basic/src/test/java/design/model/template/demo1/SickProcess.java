package design.model.template.demo1;

import cn.hutool.core.lang.Snowflake;

public abstract class SickProcess {
  public final void inquiry(){
        if(isVip ()){
            System.out.println ("您是VIP用户，无需挂号");
        }
        else{
            System.out.println ("普通病人,请等待半小时进行挂号");
            System.out.println ("您的号码为："+getNum ()+"请排队问诊");
        }
        sick ();
        pay ();
   }

    /**
     * 是否vip用户
     * @return
     */
   public abstract boolean isVip();

    /**
     * 医生看病流程
     */
   public abstract  void sick();

   public  abstract  void pay();

   private String getNum(){
       return  new Snowflake ().nextIdStr ();
   }
}
