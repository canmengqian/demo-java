package com.example.demojava;

import org.junit.jupiter.api.Test;

import java.util.Objects;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className ObjectsTest
 * @description TODO
 * @date 2023/5/10
 */
public class ObjectsTest {
    @Test
    public void testNPE() {
        System.out.println(Objects.requireNonNull("foo"));
        Objects.requireNonNull(null, () -> "值不能为空");
    }
}
