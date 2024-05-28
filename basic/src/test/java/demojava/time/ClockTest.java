package demojava.time;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.*;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className ClockTest
 * @description TODO
 * @date 2023/7/25
 */
@Slf4j
public class ClockTest {
    @Test
    void test() {
        Clock c1 = Clock.systemUTC();
        System.out.println(c1.toString());
        Clock c2 = Clock.systemDefaultZone();
        System.out.println(c2.toString());
        Clock c3 = Clock.system(ZoneId.systemDefault());
        System.out.println(c3.toString());
        // 偏移1天
        Clock c4 = Clock.offset(c1, Duration.ofDays(1));
        System.out.println(c4);
        Clock c5 = Clock.tickMinutes(ZoneId.systemDefault());
        System.out.println(c5);
        Clock c6 = Clock.tickSeconds(ZoneId.systemDefault());
        System.out.println(c6);

        Instant i1 = Clock.systemDefaultZone().instant();
        System.out.println(i1);
        // 设置负向偏移量
        Clock now = Clock.systemDefaultZone();
        System.out.println("当前时刻" + now.instant());
        Clock before = Clock.offset(now, Duration.ofDays(-1));
        System.out.println("向前偏移一天:" + before.instant());
        Clock after = Clock.offset(now, Duration.ofDays(1));
        System.out.println("向后偏移一天:" + after.instant());

    }

    @Test
    void testTick() throws InterruptedException {
        // 设置打印时钟的步长
        Clock clock = Clock.tick(Clock.systemUTC(), Duration.ofMillis(5));
        for (int i = 0; i < 10; i++) {
            System.out.println(clock.instant());
            Thread.sleep(2);
        }
    }

    @Test
    void testZone() {
        //  ZoneId.getAvailableZoneIds().forEach(System.out::println);
        Clock now = Clock.system(ZoneId.systemDefault());
        System.out.println(now.instant());
        Clock c2 = now.withZone(ZoneId.of("America/Cuiaba"));
        System.out.println(c2.instant());
    }

    @Test
    void diffDays() {
        LocalDate l1 = LocalDate.of(2023, 7, 1);
        LocalDate l2 = LocalDate.now();
        // 带有时间方向
        Period p = Period.between(l1, l2);
        log.info("相差天数:{},相差月数:{},相差年数:{}", p.getDays(), p.getMonths(), p.getYears());
        p = Period.between(l2, l1);
        log.info("相差天数:{},相差月数:{},相差年数:{}", p.getDays(), p.getMonths(), p.getYears());
    }
}
