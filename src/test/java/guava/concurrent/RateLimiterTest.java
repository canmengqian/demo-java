package guava.concurrent;

import com.google.common.util.concurrent.RateLimiter;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className RateLimiterTest
 * @description TODO
 * @date 2023/8/8
 */
public class RateLimiterTest {
    @Test
    void test() {
        RateLimiter limiter = RateLimiter.create(1, Duration.ofMillis(10));
        for (int i = 0; i < 4; i++) {
            if (limiter.tryAcquire()) {
                System.out.println("获取到令牌");
            } else {
                System.out.println("获取令牌失败");
            }
        }
        for (int i = 0; i < 10; i++) {
            double token = limiter.acquire();
            System.out.println("获取到1令牌" + token);
        }

        for (int i = 0; i < 10; i++) {
            // 每次获取10个令牌,意味着 10秒内执行一次
            double token = limiter.acquire(10);
            System.out.println("获取到10令牌" + token);
        }
        for (int i = 0; i < 10; i++) {

            boolean flag = limiter.tryAcquire(1, 1, TimeUnit.MINUTES);
            if (flag) {
                System.out.println("获取到令牌");
            } else {
                System.out.println("获取令牌失败");
            }
        }
    }
}
