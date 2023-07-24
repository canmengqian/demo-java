package com.example.demojava.time;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.*;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className OffsetDateTimeTest
 * @description TODO
 * @date 2023/7/24
 */
@Slf4j
public class OffsetDateTimeTest {
    @Test
    void getOffsetDateTime() {
        OffsetDateTime oft = OffsetDateTime.of(LocalDateTime.now(), ZoneOffset.ofHours(-8));
        System.out.println(oft.toString() + "当前小时:" + oft.getHour());
        log.info("偏移后时间:{}", oft.withOffsetSameInstant(ZoneOffset.ofHours(1)));
        // 获取偏移时间
        OffsetTime ot = OffsetTime.of(LocalTime.now(), ZoneOffset.ofHours(-8));
        log.info("当前时间：{}", ot.toString());
    }
}
