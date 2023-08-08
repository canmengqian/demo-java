package com.example.demojava.time;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Period;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className PeriodTest
 * @description TODO
 * @date 2023/7/27
 */
@Slf4j
public class PeriodTest {
    @Test
    void testDiff() {
        LocalDate l1 = LocalDate.of(2023, 7, 22);
        LocalDate l2 = LocalDate.of(2023, 7, 25);
        Period period = Period.between(l1, l2);
        log.info("想差天数:{},想差月数:{},想差年数:{},", period.getDays(), period.getMonths(), period.getYears());
        log.info("日期1>日期2{}", period.isNegative());
        Period p1 = Period.of(2023, 7, 22);
        System.out.println(p1.getDays());
        Period p2 = Period.ofDays(20);
        log.info("year:{},month:{},days:{}", p2.getYears(), p2.getMonths(), p2.getDays());
        Period p3 = Period.ofDays(100);
        log.info("year:{},month:{},days:{}", p3.getYears(), p3.getMonths(), p3.getDays());
        Period p4 = Period.ofWeeks(100);
        log.info("year:{},month:{},days:{}", p4.getYears(), p4.getMonths(), p4.getDays());
        Period p5 = Period.ofMonths(10);
        log.info("year:{},month:{},days:{}", p5.getYears(), p5.getMonths(), p5.getDays());
    }
}
