package com.example.demojava;

import org.junit.jupiter.api.Test;

import java.util.Optional;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className OptionalTest
 * @description TODO
 * @date 2023/5/10
 */
public class OptionalTest {
    @Test
    public void test1() {
        Optional<String> o1 = Optional.empty();
        System.out.println(o1);
        Optional<String> o2 = Optional.of("foo");
        System.out.println(o2);
        // null 抛出 java.lang.NullPointerException
        //Optional<String> o3 = Optional.of(null);
        //System.out.println(o3);

        Optional<String> o3 = Optional.ofNullable(null);
        System.out.println(o3);

        String name = "foo";
        Optional<String> o4 = Optional.of(name);
        // 判断是否存在值
        if (o4.isPresent()) {
            System.out.println("值存在");
            o2.ifPresent(s -> {
                System.out.println(s);
            });
        }
    }

    @Test
    public void test2() {
        // 设置默认值
        String name = null;
        Optional<String> o4 = Optional.ofNullable(name);
        // 如果原始值为null,使用get()获取结果时仍然会抛出 NoSuchElementException
        System.out.println(o4.get());
    }

    @Test
    public void testDefValIfNoNull() {
        // 设置默认值,orElse在值不为空的情况下仍然会调用,orElseGet并不执行默认值的获取
        String name = "foo";
        String name2 = Optional.ofNullable(name).orElse(getDefaultValue());
        System.out.println("orElse" + name2);
        String name3 = Optional.ofNullable(name).orElseGet(OptionalTest::getDefaultValue);
        System.out.println("orElseGet:" + name3);
    }

    public static String getDefaultValue() {
        System.out.println("def");
        return "def";
    }
}
