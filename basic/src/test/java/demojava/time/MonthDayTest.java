package demojava.time;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.*;
import java.time.temporal.ChronoField;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className MonthDayTest
 * @description TODO
 * @date 2023/7/28
 */
@Slf4j
public class MonthDayTest {
    @Test
    void test() {
        MonthDay md = MonthDay.from(LocalDate.now());
        log.info("md:{},month:{},days:{}", md, md.getMonthValue(), md.getDayOfMonth());
        // Unable to obtain MonthDay from TemporalAccessor: 2023-07-28T08:28:41.218Z of type java.time.Instant
      /*  md = MonthDay.from(Instant.now());
        log.info("md:{},month:{},days:{}", md, md.getMonthValue(), md.getDayOfMonth());
        */
        md = MonthDay.now(ZoneId.systemDefault());
        log.info("md:{},month:{},days:{}", md, md.getMonthValue(), md.getDayOfMonth());
        //TODO 比较
        MonthDay md2 = MonthDay.from(LocalDate.now().minusMonths(1));
        log.info("md > md2 ?{}", md.isAfter(md2));
        log.info("md2 < md ?{}", md2.isBefore(md));
        log.info("md2 = md ?{}", md2.equals(md));
        log.info("md2 is Supported YEAR ?{}", md2.isSupported(ChronoField.YEAR));
        log.info("md2 is Valid Year ?{}", md2.isValidYear(2022));

    }
}
