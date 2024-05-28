package guava.retry;

import com.github.rholder.retry.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className RetryTest
 * @description TODO
 * @date 2023/8/9
 */
@Slf4j
public class RetryTest {
    @Test
    void test() throws ExecutionException, RetryException, InterruptedException {
      Retryer<Integer> retryer=  RetryerBuilder.<Integer>newBuilder()
                .retryIfException()//抛出异常则进行重试
                .retryIfExceptionOfType(NumberFormatException.class) //抛出具体指定的异常进行重试
                .retryIfResult(Objects::isNull)
                .retryIfRuntimeException()
                .withRetryListener(new RetryListener() {
                    @Override
                    public <V> void onRetry(Attempt<V> attempt) {
                        log.info("当前尝试次数，{}",attempt.getAttemptNumber());
                    }
                })
                // 重试三次后停止
                .withStopStrategy(StopStrategies.stopAfterAttempt(3))
                .withBlockStrategy(BlockStrategies.threadSleepStrategy())
                .withWaitStrategy(WaitStrategies.exponentialWait(1,TimeUnit.SECONDS))
                .build();
        retryer.call(() -> {
            /*if(1>0){
                System.out.println("抛出异常");
                throw new NumberFormatException();
            }*/
            return 1;
        });
        Thread.currentThread().join(10000);


    }
}
