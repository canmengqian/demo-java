package com.example.demojava.invoke;

import org.junit.jupiter.api.Test;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className MethodHandlerDemo
 * @description TODO
 * @date 2023/5/12
 */
public class MethodHandlerDemo {
    @Test
    public void test() throws Throwable {
        Animal cat = new Cat();
        Animal dog = new Dog();
        anyEat(cat);
        anyEat(dog);
    }

    public void anyEat(Object obj) throws Throwable {
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodHandle methodHandle = lookup.findVirtual(obj.getClass(), "eat", MethodType.methodType(void.class));
        methodHandle.invoke(obj);
    }

    interface Animal {
        void eat();
    }

    class Cat implements Animal {
        @Override
        public void eat() {
            System.out.println("猫吃饭");
        }
    }

    class Dog implements Animal {
        @Override
        public void eat() {
            System.out.println("狗吃饭");
        }
    }
}
