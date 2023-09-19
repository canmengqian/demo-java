package com.example.demojava.lang.reflect;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className ClassTest
 * @description TODO
 * @date 2023/9/11
 */
public class ClassTest {
    void test() {
        Class<ClassTest> clazz = (Class<ClassTest>) this.getClass();
        clazz.getSimpleName();
        clazz.asSubclass(null);
        clazz.describeConstable();
        clazz.descriptorString();
        clazz.desiredAssertionStatus();

    }
}
