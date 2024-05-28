package demojava.time;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

@Slf4j
public class TemporalAdjusterTest {
    @Test
    void test() {
        LocalDate l = LocalDate.now ();
        TemporalAdjuster aj = TemporalAdjusters.firstDayOfNextMonth ();
        LocalDate l1 = l.with (aj);
        log.info ("now date:{}",l);
        log.info ("下个月第一天:{}",l1);
        aj = TemporalAdjusters.firstDayOfMonth ();
        l1 = l.with (aj);
        log.info ("当月第一天:{}",l1);
        aj = TemporalAdjusters.lastDayOfMonth ();
        l1 = l.with (aj);
        log.info ("当月最后一天:{}",l1);
        aj = TemporalAdjusters.firstDayOfYear ();
        l1 = l.with (aj);
        log.info ("当年第一天:{}",l1);
        aj = TemporalAdjusters.lastDayOfYear ();
        l1 = l.with (aj);
        log.info ("当年最后一天:{}",l1);

        aj = TemporalAdjusters.firstInMonth (DayOfWeek.FRIDAY);
        l1 = l.with (aj);
        log.info ("当月第一个周五:{}",l1);

        aj = TemporalAdjusters.lastInMonth (DayOfWeek.FRIDAY);
        l1 = l.with (aj);
        log.info ("当月最后一个周五:{}",l1);


    }
}
