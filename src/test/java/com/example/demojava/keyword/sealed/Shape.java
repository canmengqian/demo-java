package com.example.demojava.keyword.sealed;

/**
 * @author zhengzz
 * @version 1.0.0
 * @description TODO
 * @date 2023/9/5
 */
public sealed interface Shape permits Cycle, ShapeTest.MyShape, Squrad {

    double area();

}
