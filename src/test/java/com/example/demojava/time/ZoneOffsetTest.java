package com.example.demojava.time;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.ZoneOffset;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className ZoneOffsetTest
 * @description TODO
 * @date 2023/7/24
 */
@Slf4j
public class ZoneOffsetTest {
    @Test
    void  getZoneOffset(){
        log.info(ZoneOffset.ofHours(8).toString());
        log.info(ZoneOffset.ofHoursMinutes(8,8).toString());
        log.info(ZoneOffset.ofHoursMinutesSeconds(8,8,8).toString());
        log.info(ZoneOffset.MIN.toString());
        log.info(ZoneOffset.MAX.toString());
    }
}
