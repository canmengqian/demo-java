package resilience4j.retry;

import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.retry.RetryRegistry;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.concurrent.Callable;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className RetryTest
 * @description TODO
 * @date 2023/8/17
 */
public class RetryTest {
    @Test
    void test() throws Exception {
        RetryConfig config = new RetryConfig.Builder<Boolean>()
                .retryExceptions(RuntimeException.class)
                .retryOnException(t -> t instanceof Exception)
                // 返回结果为false则进行重试
                .retryOnResult(Boolean.FALSE::equals)
                // 在重试之前对返回的结果进行处理,只计算重试次数-1 的记录
                .consumeResultBeforeRetryAttempt((integer, o) -> {
                    System.out.println("当前重试次数：" + integer + "执行结果：" + o);
                })
                // 重试完成仍不能正常执行时是否抛出异常
                .failAfterMaxAttempts(true)
                .ignoreExceptions(NumberFormatException.class, NullPointerException.class)
                // 修改失败后等待间隔的函数。默认情况下，等待时间是个常量。
                .intervalBiFunction((integer, et) -> 1L)
                .writableStackTraceEnabled(false)
                // 重试间隔
                .waitDuration(Duration.ofSeconds(1))
                .build();
        // Context
        // AsyncContext
        Retry retry = RetryRegistry.of(config).retry("test");
        retry.executeCallable(new Callable<Boolean>() {
            static int count = 0;

            @Override
            public Boolean call() throws Exception {
                count++;
                System.out.println("当前计数器的值为："+count);
                return false;
            }
        });

    }
}
