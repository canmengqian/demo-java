package com.example.demojava.function;

import org.junit.jupiter.api.Test;

import java.util.function.Function;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className FunctionTest
 * @description TODO
 * @date 2023/5/11
 */
public class FunctionTest {
    @Test
    public void testFunc() {
        Function<Integer, Integer> f1 = i -> i * 5;
        Function<Integer, Integer> f2 = i -> i + 5;
        System.out.println(f1.apply(2));
        System.out.println(f2.apply(0));

        // 组合运算compose ,先计算函数内的func,再计算发起组合的func
        System.out.println(f1.compose(f2).apply(1));
        // 连续运算，先计算发起的func,再计算andThen里的func
        System.out.println(f1.andThen(f2).apply(1));
        // 混合计算
        System.out.println(f1.compose(f2).andThen(f1).andThen(f1).apply(1));
        System.out.println(f1.compose(f2).andThen(f1).compose(f1).apply(1));
        System.out.println(f1.compose(f2).compose(f1).andThen(f1).apply(1));
    }
}
