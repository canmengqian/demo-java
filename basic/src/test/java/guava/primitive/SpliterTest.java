package guava.primitive;

import com.google.common.base.Splitter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

@Slf4j
public class SpliterTest {
    @Test
    void test(){
        // limit指代切分几次
        Splitter.on (",").limit (2)
                .splitToList ("1,2,3,4")
                .forEach (System.out::println);
        // limit超过待切分的字符串也不会报错,按照允许最大的切分长度进行切分
        Splitter.on (",").limit (10)
                .splitToList ("1,2,3,4")
                .forEach (System.out::println);
        // 去除空格,并转换成流
        System.out.printf ("去除空格,并转换成流");
        Splitter.on (",").limit (10)
                .trimResults ().splitToStream ("  1,2,3").forEach (System.out::println);
        // 字符串转map
        System.out.printf ("字符串转map");
        Splitter.on (",").withKeyValueSeparator ("=").split ("name=zhangsan,age=25").forEach ((k,v)->{
            System.out.println ("Key:"+k + "\t value:"+v);
        });
        System.out.printf ("按正则切分");
        // 按正则切分
        Splitter.on (Pattern.compile ("[,|.]")).splitToList ("1,2.3").forEach (System.out::println);
    }
}
