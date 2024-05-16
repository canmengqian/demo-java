package com.example.demojava.lang.invoke;

import org.junit.jupiter.api.Test;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className MethodHandleTest
 * @description TODO
 * @date 2023/12/14
 */
public class MethodHandleTest {
    static class Person {
        public static void eat(String food) {
            System.out.println("正在吃" + food);
        }

        public void sing(String song) {
            System.out.println("正在唱" + song);
        }
    }


    @Test
    void testInvokeInstantMethod() throws Throwable {
        Person p = new Person();
        MethodType mt = MethodType.methodType(void.class, String.class);
        MethodHandle handle = MethodHandles.lookup().findVirtual(p.getClass(), "sing", mt).bindTo(p);
        handle.invoke("大海");
        handle.invokeExact("大海");
        handle.invokeWithArguments("大海");
        // 测试静态方法
        handle=MethodHandles.lookup().findStatic(p.getClass(),"eat", MethodType.methodType(void.class, String.class));
        handle.invoke("土豆");

    }
}
