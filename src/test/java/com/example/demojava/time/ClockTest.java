package com.example.demojava.time;

import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Duration;
import java.time.ZoneId;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className ClockTest
 * @description TODO
 * @date 2023/7/25
 */
public class ClockTest {
    @Test
    void test() {
        Clock c1 = Clock.systemUTC();
        System.out.println(c1.toString());
        Clock c2 = Clock.systemDefaultZone();
        System.out.println(c2.toString());
        Clock c3 = Clock.system(ZoneId.systemDefault());
        System.out.println(c3.toString());
        // 偏移1天
        Clock c4 = Clock.offset(c1, Duration.ofDays(1));
        System.out.println(c4);
        Clock c5 = Clock.tickMinutes(ZoneId.systemDefault());
        System.out.println(c5);
        Clock c6 = Clock.tickSeconds(ZoneId.systemDefault());
        System.out.println(c6);
    }
}
