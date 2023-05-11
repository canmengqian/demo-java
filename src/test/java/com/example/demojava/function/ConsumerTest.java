package com.example.demojava.function;

import org.junit.jupiter.api.Test;

import java.util.function.Consumer;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className ConsumerTest
 * @description TODO
 * @date 2023/5/11
 */
public class ConsumerTest {
    @Test
    public void testConsumer() {
        Consumer<Integer> c1 = System.out::println;
        Consumer<Integer> c2 = i -> {
            i += 5;
            System.out.println(i);
        };
        c1.accept(5);
        c2.accept(5);
        // 不管有多少个andThen()方法,都以accept作为入参,不会发生参数传递
        c1.andThen(c2)
                .andThen(c2)
                .andThen(c2)
                .andThen(c2)
                .accept(5);


    }
}
