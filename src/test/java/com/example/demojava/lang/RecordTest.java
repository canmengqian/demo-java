package com.example.demojava.lang;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className RecordTest
 * @description TODO
 * @date 2023/8/31
 */
@Slf4j
public class RecordTest {
    public record RecordPerson(String name) {
    }

    public record X(String x) {
        static String x_p;

        public static void setX_p(String x_p) {
            X.x_p = x_p;
        }
    }

    @Test
    void test() {
        var p = new RecordPerson("zhengzz");
        log.info(p.toString());

        var x = new X("x");
        log.info(x.x());
    }
}
