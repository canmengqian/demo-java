package com.example.demojava.keyword.sealed;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class ShapeTest {

    @Test
    void area() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Shape s1 = new Squrad(2, 2);
        log.info("the squrad area is {}", s1.area());
        Shape s2 = new Cycle(2);
        log.info("the cycle area is {}", s2.area());

        Shape s3 = new MyShape();
        log.info("s3的 area is{}", s3.area());
        Class clzz = Class.forName("com.example.demojava.keyword.sealed.Squrad");
        log.info(clzz.getConstructor(double.class, double.class).newInstance(2, 1).getClass().toString());
    }

    static final class MyShape implements Shape {
        @Override
        public double area() {
            return 0;
        }
    }
}