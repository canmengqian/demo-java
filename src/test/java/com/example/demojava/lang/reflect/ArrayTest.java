package com.example.demojava.lang.reflect;

import domain.bean.Person;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className ArrayTest
 * @description TODO
 * @date 2023/9/5
 */
@Slf4j
public class ArrayTest {
    @Test
    void test() {
        int[] ints = (int[]) Array.newInstance(int.class, 10);
        Array.set(ints, 0, 1);
        System.out.println(Array.getInt(ints, 0));
        Arrays.stream(ints).forEach(System.out::println);

        Person[] ps = (Person[]) Array.newInstance(getClazz(new Person()), 10);
        Array.set(ps, 0, new Person("李四"));
        log.info("人物信息:{}", Array.get(ps, 0).toString());
    }

    public static <T> Class<T> getClazz(T t) {
        return (Class<T>) t.getClass();
    }
}
