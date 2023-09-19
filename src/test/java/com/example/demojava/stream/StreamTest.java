package com.example.demojava.stream;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.function.Supplier;
import java.util.stream.Stream;

@Slf4j
public class StreamTest {
    /**
     * 代码优化
     */
    @Test
    public  void test(){
        /**
         * 重用Stream
         */
        Supplier<Stream<String>> streamSupplier = ()-> Stream.of ("1","2","3");
        streamSupplier.get ().forEach (System.out::println);
        streamSupplier.get ().filter (s->s.equals ("1")).forEach (System.out::println);

        /**
         * 异常处理
         */
        log.info ("异常处理");
        Stream.of ("a","1","2","3").map (this::str2Int).forEach (System.out::println);
    }

    public Integer str2Int(String str){
        try {
            return  Integer.parseInt (str);
        }catch (Exception e){
            return 0;
        }
    }
}
