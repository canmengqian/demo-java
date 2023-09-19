package com.example.demojava.lang.reflect;

import java.lang.reflect.Method;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className MethodTest
 * @description TODO
 * @date 2023/9/11
 */
public class MethodTest {
    void test() {
        Method[] methods = this.getClass().getDeclaredMethods();
        for (Method method : methods) {
            method.getModifiers();
            method.getAnnotatedReturnType();
            method.getDeclaredAnnotations();
            method.getExceptionTypes();

            // 泛型异常类型
            method.getGenericExceptionTypes();
            // 泛型参数类型
            method.getGenericParameterTypes();
            // 泛型返回类型
            method.getGenericReturnType();
            // 参数个数
            method.getParameterCount();
            // 参数类型
            method.getParameterTypes();
            // 返回类型
            method.getReturnType();

            method.getDefaultValue();
            // 所有参数
            method.getParameters();

            // 桥接方法
            method.isBridge();
            // 默认方法
            method.isDefault();
            // 合成方法
            method.isSynthetic();
            // 可变参方法
            method.isVarArgs();
            method.trySetAccessible();

            //

        }

    }
}
