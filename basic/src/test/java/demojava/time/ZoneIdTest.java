package demojava.time;

import cn.hutool.core.io.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.*;
import java.time.format.TextStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.time.zone.ZoneRules;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className ZoneIdTest
 * @description TODO
 * @date 2023/6/21
 */
@Slf4j
class ZoneIdTest {

    @Test
    void getZoneId(){
        System.out.println("从LocalDateTime获取："+ZoneId.from(LocalDateTime.now()));
        System.out.println("从LocalDate获取："+ZoneId.from(LocalDate.now()));
        System.out.println("从ZonedDateTime获取："+ZoneId.from(ZonedDateTime.now()));
    }
    @Test
    void test() {
        // 获取可用的时区
        //  ZoneId.getAvailableZoneIds().forEach(System.out::println);
        ZoneId zoneId = ZoneId.of("Asia/Aden");
        // TODO
        ZoneRules rules = ZoneId.of("Asia/Aden").getRules();
        log.info("ID:{}", zoneId.getId());
        // 根据指定的地区显示不同的语言, TextStyle用来描述简称或全称
        log.info(zoneId.getDisplayName(TextStyle.FULL, Locale.JAPANESE));
        log.info(zoneId.getDisplayName(TextStyle.SHORT, Locale.CHINA));
        log.info(zoneId.getDisplayName(TextStyle.NARROW, Locale.CHINA));
        log.info(zoneId.getDisplayName(TextStyle.NARROW_STANDALONE, Locale.CHINA));
        log.info(zoneId.getDisplayName(TextStyle.FULL_STANDALONE, Locale.CHINA));
        log.info(zoneId.getDisplayName(TextStyle.SHORT_STANDALONE, Locale.CHINA));
        log.info("{}", zoneId.normalized().toString());
        log.info("获取系统默认的时区：{}", ZoneId.systemDefault().toString());
        /*
        java.lang.IllegalArgumentException: prefix should be GMT, UTC or UT, is: Test
        时区只能为GMT, UTC or UT
         */
        log.info("时区偏移量:{}", ZoneId.ofOffset("GMT", ZoneOffset.ofHours(0)));
        log.info("时区偏移量:{}", ZoneId.ofOffset("GMT", ZoneOffset.ofHours(8)));
        log.info("时区偏移量:{}", ZoneId.ofOffset("GMT", ZoneOffset.ofHours(-8)));
        // 使用UTC
        log.info("时区偏移量:{}", ZoneId.ofOffset("UTC", ZoneOffset.ofHours(0)));
        log.info("时区偏移量:{}", ZoneId.ofOffset("UTC", ZoneOffset.ofHours(8)));
        log.info("时区偏移量:{}", ZoneId.ofOffset("UTC", ZoneOffset.ofHours(-8)));


    }

    @Test
    void test2() {
        ZoneId.SHORT_IDS.forEach((k, v) -> {
            log.info("key:{},value:{}", k, v);
        });
    }

    /**
     * @return
     * @Param
     * @author zhengzz
     * @description java.util.Date 与 java.time.LocalDate 之间的转换
     * @date 15:43 2023/7/24
     **/
    @Test
    void date2LocalDate() {
        System.out.println(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        System.out.println(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
    }

    @Test
    void LocalDate2Date() {
        System.out.println(Date.from(LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
        System.out.println(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
    }

    /**
     * @return
     * @Param
     * @author zhengzz
     * @description 设置指定时间的时区
     * @date 15:56 2023/7/24
     **/
    @Test
    void setLocalTimeByZoneId() {
        LocalTime l1 = LocalTime.now(ZoneId.systemDefault());
        System.out.println(l1);
    }

    @Test
    void TimeZone2ZoneId() {
        System.out.println(TimeZone.getDefault().toZoneId().toString());
    }

    @Test
    void getZoneTime() {
        System.out.println(ZonedDateTime.of(LocalDateTime.now(), ZoneId.systemDefault()));
        System.out.println(OffsetDateTime.of(LocalDateTime.now(), ZoneOffset.ofHours(1)));

    }

    @Test
    void test3() {
        // 获取时区ID，秒数，调整时间,时间比较
        ZoneOffset offset = ZoneOffset.from(ZonedDateTime.now());
        log.info("获取时区ID:{},与GMT标准时间0时区以来相差秒数:{}", offset.getId(), offset.getTotalSeconds());
        log.info("获取指定时间字段,year:{},month:{}", offset.getLong(ChronoField.YEAR), offset.getLong(ChronoField.MONTH_OF_YEAR));
    }


}
