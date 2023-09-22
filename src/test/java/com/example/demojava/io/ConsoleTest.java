package com.example.demojava.io;

import org.junit.jupiter.api.Test;

import java.io.Console;
import java.io.PrintWriter;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className ConsoleTest
 * @description TODO
 * @date 2023/9/22
 */
public class ConsoleTest {
    @Test
    void test() {
        Console console = System.console();
        // 向屏幕进行输出
        PrintWriter pw = console.writer();
    }
}
