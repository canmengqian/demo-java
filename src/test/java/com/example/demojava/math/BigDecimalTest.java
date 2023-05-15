package com.example.demojava.math;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className BigDecimalTest
 * @description TODO
 * @date 2023/5/11
 */
public class BigDecimalTest {
    @Test
    public void test() {
        float d1 = 1.10f;
        System.out.println(d1);
        BigDecimal b1 = BigDecimal.valueOf(d1);
        System.out.println(b1);
        System.out.println(b1.floatValue()==d1);
    }
}
