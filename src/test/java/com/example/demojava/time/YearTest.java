package com.example.demojava.time;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.*;
import java.time.temporal.*;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className YearTest
 * @description TODO
 * @date 2023/7/28
 */
@Slf4j
public class YearTest {
    @Test
    void test() {
        Year year = Year.from(LocalDate.now());
        log.info("year:{},{},{}", year.getValue(), year.get(ChronoField.YEAR), year.getLong(ChronoField.YEAR));
        Year y2 = Year.of(2024);
        log.info("year:{},{},{}", y2.getValue(), y2.get(ChronoField.YEAR), y2.getLong(ChronoField.YEAR));
        Year y3 = Year.now(Clock.systemDefaultZone());
        log.info("year:{},{},{}", y3.getValue(), y3.get(ChronoField.YEAR), y3.getLong(ChronoField.YEAR));
        Year y4 = Year.now(ZoneId.systemDefault());
        log.info("year:{},{},{}", y4.getValue(), y4.get(ChronoField.YEAR), y4.getLong(ChronoField.YEAR));

        // 根据天数计算当地的日期
        LocalDate l1 = year.atDay(1);
        log.info("l1:{}", l1);
        YearMonth ym = year.atMonth(Month.OCTOBER);
        log.info("ym:{}", ym);
        // 根据月份计算当地年月
        YearMonth ym2 = year.atMonth(1);
        log.info("ym2:{}", ym2);
        // 根据月日来计算当地日期
        LocalDate l2 = year.atMonthDay(MonthDay.now());
        log.info("l2{}", l2);

        // 仅支持年份加减
        Year y1 = year.plus(Period.ofYears(100));
        log.info("y1对应的年份：{}", y1);
        y1 = year.plus(1, ChronoUnit.YEARS);
        log.info("y1对应的年份：{}", y1);
        y1 = year.plusYears(100);
        log.info("y1对应的年份：{}", y1);
        // 减年份
        y1 = year.minus(Period.ofYears(100));
        log.info("y1对应的年份：{}", y1);
        y1 = year.minus(100, ChronoUnit.YEARS);
        log.info("y1对应的年份：{}", y1);
        y1 = year.minusYears(100);
        log.info("y1对应的年份：{}", y1);
       /*
       // java.time.temporal.UnsupportedTemporalTypeException: Unsupported unit: Months
       Period.ofMonths(100);
       Year y1 = year.plus(Period.ofDays(100));
        log.info("y1{}", y1);*/

        // 年份比较
        Year y5 = Year.of(2023);
        Year y6 = Year.of(2023);
        Year y7 = Year.of(2024);
        log.info("是否闰年:{}", y6.isLeap());
        log.info("y6<y7:{}", y6.isBefore(y7));
        log.info("y7>y6:{}", y7.isAfter(y6));
        log.info("y5=y6:{}", y5.equals(y6));
    }
}
