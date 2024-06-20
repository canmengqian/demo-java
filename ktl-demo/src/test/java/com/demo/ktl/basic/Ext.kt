package com.demo.ktl.basic


import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.Serializable

fun Serializable.logger(): Logger {
    return LoggerFactory.getILoggerFactory().getLogger(this.javaClass.name);
}

fun Serializable.abs(n: Double): Double {
    return Math.abs(n)
}

fun TypeDemo.desc_1(str: String): Any {
    return str
}

val TypeDemo.desc get() = { println("这是一个简单的描述") }
