package resilience4j.timelimit;

import io.github.resilience4j.timelimiter.TimeLimiter;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import io.github.resilience4j.timelimiter.TimeLimiterRegistry;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

import static java.util.concurrent.CompletableFuture.supplyAsync;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className TimeLimitTest
 * @description TODO
 * @date 2023/8/24
 */
public class TimeLimitTest {
    @Test
    void test() throws Exception {
        TimeLimiterConfig config = TimeLimiterConfig.custom()
                //  超时后是否取消Future的运行
                .cancelRunningFuture(true)
                // 方法最长执行时间,超时后抛出异常
                .timeoutDuration(Duration.ofSeconds(1))
                .build();
        // 注册超时配置
        TimeLimiterRegistry registry = TimeLimiterRegistry.of(config);
        TimeLimiter timeLimiter = registry.timeLimiter("test1", config);

        String rs = String.valueOf(timeLimiter.executeFutureSupplier((Supplier<Future<String>>) () -> CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "正常执行";
        })));
        System.out.println(rs);

        String err = String.valueOf(timeLimiter.executeFutureSupplier((Supplier<Future<String>>) () -> CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "方法超时";
        })));
        System.out.println(err);
    }
}
