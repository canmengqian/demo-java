package com.example.demojava.time;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className ZonedDateTimeTest
 * @description TODO
 * @date 2023/7/24
 */
@Slf4j
public class ZonedDateTimeTest {
    @Test
    void getAndFmt(){
        ZonedDateTime zdt = ZonedDateTime.of(LocalDateTime.now(), ZoneId.systemDefault());
        log.info(DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(zdt));
        log.info(DateTimeFormatter.ISO_LOCAL_DATE.format(zdt));
        log.info(DateTimeFormatter.ISO_LOCAL_TIME.format(zdt));
        log.info(DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(zdt));
    }
}
