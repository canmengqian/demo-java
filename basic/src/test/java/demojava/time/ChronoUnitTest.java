package demojava.time;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Slf4j
public class ChronoUnitTest {
    @Test
    void testAdd() {
        LocalDateTime l = LocalDateTime.now ();

        log.info ("当前时间:{}", l);
        l = ChronoUnit.MILLIS.addTo (LocalDateTime.now (), 1);
        log.info ("加一毫秒:{}", l);

        l = ChronoUnit.SECONDS.addTo (LocalDateTime.now (), 1);
        log.info ("加一秒:{}", l);

        l = ChronoUnit.MINUTES.addTo (LocalDateTime.now (), 1);
        log.info ("加一分钟:{}", l);

        l = ChronoUnit.HOURS.addTo (LocalDateTime.now (), 1);
        log.info ("加一小时:{}", l);
        l = ChronoUnit.DAYS.addTo (LocalDateTime.now (), 1);
        log.info ("加一天:{}", l);
        l = ChronoUnit.MONTHS.addTo (LocalDateTime.now (), 1);
        log.info ("加一月:{}", l);
        l = ChronoUnit.YEARS.addTo (LocalDateTime.now (), 1);
        log.info ("加一年:{}", l);
        l = ChronoUnit.CENTURIES.addTo (LocalDateTime.now (), 1);
        log.info ("加一世纪:{}", l);
        l = ChronoUnit.MILLENNIA.addTo (LocalDateTime.now (), 1);
        log.info ("加一千年:{}", l);
        l = ChronoUnit.ERAS.addTo (LocalDateTime.now (), 0);
        log.info ("加一纪元(十亿年):{}", l);
    }

    @Test
    void testBetween() {
        LocalDateTime l = LocalDateTime.now ();
        LocalDateTime l1 = l.plus (1, ChronoUnit.DAYS);
        long diff = ChronoUnit.DAYS.between (l, l1);
        log.info ("相差天数：{}", diff);
        LocalDateTime l2 = l.plus (1, ChronoUnit.MONTHS);
        diff = ChronoUnit.MONTHS.between (l, l2);
        log.info ("相差月数：{}", diff);
        LocalDateTime l3 = l.plus (1, ChronoUnit.YEARS);
        diff = ChronoUnit.YEARS.between (l, l3);
        log.info ("相差年数：{}", diff);

    }
}
