package demojava.lang;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class InheritableThreadLocalTest {
    private static  ThreadLocal<Person> TH = new InheritableThreadLocal<> ();
    @Test
    void test(){
        TH.set ( Person.builder ().name ("张三").build ());
        log.info ("main,{}",TH.get ().toString ());
      Thread t1=  new Thread (){
            @Override
            public void run() {
                System.out.println (TH.get ().toString ());
                TH.set (Person.builder ().name ("lisi").build ());
                System.out.println (TH.get ().toString ());
            }
        };
      t1.run ();

        log.info ("main,{}",TH.get ().toString ());
        TH.set ( Person.builder ().name ("wangwu").build ());
        log.info ("main,{}",TH.get ().toString ());
        t1.run ();
    }
}
