package com.example.demojava.lang;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className ClassLoaderTest
 * @description TODO
 * @date 2023/9/4
 */
public class ClassLoaderTest {
    void  test(){
        this.getClass().getResource("");
       String.class.getClassLoader().getResource("");
       ClassLoader classLoader = ClassLoader.getSystemClassLoader();
       classLoader.getResource("");
    }
}
