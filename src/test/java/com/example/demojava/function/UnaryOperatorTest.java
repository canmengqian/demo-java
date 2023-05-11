package com.example.demojava.function;

import org.junit.jupiter.api.Test;

import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className UnaryOperatorTest
 * @description TODO
 * @date 2023/5/11
 */
public class UnaryOperatorTest {
    @Test
    public void testFunc() {
        UnaryOperator<Integer> u1 = i-> i * 5;
        UnaryOperator<Integer> u2 = i-> i + 5;
        System.out.println(u1.apply(1));

    }
}
