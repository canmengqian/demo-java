package resilience4j.ratelimit;


import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.concurrent.Callable;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className RateLimitTest
 * @description TODO
 * @date 2023/8/28
 */
@Slf4j
public class RateLimitTest {

    @Test
    void test() {
        RateLimiterConfig config = RateLimiterConfig.custom()
                .limitForPeriod(1)
                .limitRefreshPeriod(Duration.ofSeconds(5))
                .timeoutDuration(Duration.ofSeconds(2))
                .writableStackTraceEnabled(true)
                .build();
        RateLimiterRegistry registry = RateLimiterRegistry.custom().addRateLimiterConfig("test-cfg1", config).build();
        RateLimiter rateLimiter = registry.rateLimiter("r1", "test-cfg1");
        rateLimiter.executeRunnable(() -> {
            try {
                Thread.sleep(1000);
                System.out.println("执行限流线程1");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        // 被限流
        rateLimiter.executeRunnable(() -> {
            try {
                System.out.println("执行限流线程2");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }

    /**
     * @return
     * @Param
     * @author zhengzz
     * @description 因超时进行限流
     * @date 14:48 2023/8/28
     **/
    @Test
    void testTimeOut() {
        RateLimiterConfig config = RateLimiterConfig.custom()
                .limitForPeriod(1)
                .limitRefreshPeriod(Duration.ofSeconds(5))
                //获取执行令牌时最大等待超时时间
                .timeoutDuration(Duration.ofSeconds(2))
                .writableStackTraceEnabled(true)
                .build();
        RateLimiterRegistry registry = RateLimiterRegistry.custom().addRateLimiterConfig("test-cfg1", config).build();
        RateLimiter rateLimiter = registry.rateLimiter("r1", "test-cfg1");
        // 设置事件
        RateLimiter.EventPublisher publisher = rateLimiter.getEventPublisher().onFailure(c -> {
            log.info("限流器名称：{},事件类型：{},获取令牌数：{}", c.getRateLimiterName(),
                    c.getEventType().name()
                    , c.getNumberOfPermits()
            );
        }).onSuccess(c -> {
            log.info("限流器名称：{},事件类型：{},获取令牌数：{}", c.getRateLimiterName(),
                    c.getEventType().name()
                    , c.getNumberOfPermits()

            );
        });
        // 因为超时限流
        rateLimiter.executeRunnable(() -> {
            try {
                Thread.sleep(3500);
                System.out.println("执行限流线程1");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        // 因为等待两秒后可以获取令牌,该线程正常执行
        rateLimiter.executeRunnable(() -> {
            try {
                System.out.println("执行限流线程2");
                Thread.sleep(3500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        rateLimiter.acquirePermission();
        rateLimiter.executeRunnable(() -> {
            try {
                Thread.sleep(1000);
                System.out.println("执行限流线程3");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    void testWithTry() {
        RateLimiterConfig config = RateLimiterConfig.custom()
                .limitForPeriod(1)
                .limitRefreshPeriod(Duration.ofSeconds(5))
                //获取执行令牌时最大等待超时时间
                .timeoutDuration(Duration.ofSeconds(2))
                .writableStackTraceEnabled(true)
                .build();
        RateLimiterRegistry registry = RateLimiterRegistry.custom().addRateLimiterConfig("test-cfg1", config).build();

        RateLimiter rateLimiter = registry.rateLimiter("r1", "test-cfg1");
        Boolean val = Try.ofCallable(RateLimiter.decorateCallable(rateLimiter, (Callable<Boolean>) () -> {
            System.out.println("即将发生异常");
            throw new IllegalArgumentException();
        })).recover(e -> {
            System.out.println("捕捉到异常并进行恢复");
            return false;
        }).onSuccess(c -> {
            System.out.println("数据处理成功");
        }).get();
        System.out.println("获取到的值为" + val);
    }
}
