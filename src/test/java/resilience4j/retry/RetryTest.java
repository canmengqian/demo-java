package resilience4j.retry;

import io.github.resilience4j.core.functions.CheckedFunction;
import io.github.resilience4j.core.functions.CheckedRunnable;
import io.github.resilience4j.core.functions.CheckedSupplier;
import io.github.resilience4j.micrometer.tagged.TaggedRetryMetrics;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.retry.RetryRegistry;
import io.micrometer.core.instrument.Meter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.stream.StreamSupport;


/**
 * @author zhengzz
 * @version 1.0.0
 * @className RetryTest
 * @description TODO
 * @date 2023/8/17
 */
@Slf4j
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
                System.out.println("当前计数器的值为：" + count);
                return false;
            }
        });

    }

    @Test

    public void test2() throws Throwable {

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
                .failAfterMaxAttempts(false)
                .ignoreExceptions(NumberFormatException.class, NullPointerException.class)
                // 修改失败后等待间隔的函数。默认情况下，等待时间是个常量。与waitDuration互斥
                .intervalBiFunction((integer, et) -> 1L)
                .writableStackTraceEnabled(false)
                // 重试间隔
                .waitDuration(Duration.ofSeconds(1))
                .build();
        RetryRegistry registry = RetryRegistry.of(config);
        MeterRegistry meterRegistry = new SimpleMeterRegistry();
        TaggedRetryMetrics.ofRetryRegistry(registry).bindTo(meterRegistry);
        Retry retry = registry.retry("test2");
        Retry.decorateCheckedSupplier(retry, new CheckedSupplier<Boolean>() {
            static int count = 0;

            @Override
            public Boolean get() {
                count++;
                System.out.println("当前计数器的值为：" + count);
                return false;
            }
        }).andThen((b) -> {
            // 抑制异常才能执行该方法
            if (b == null || Boolean.FALSE.equals(b)) {
                log.info("无法请求成功{}", b);
            }
            return true;
        }).unchecked().get();

        //相对于decorateCheckedSupplier无法执行andThen方法
        Retry.decorateSupplier(retry, () -> {
            log.info("supplier");
            return false;
        }).get();
        // fundction
        Retry.decorateCheckedFunction(retry, o -> {
            System.out.println(o);
            return false;
        }).apply(Integer.toString(1));

        log.warn("decorateFunction");
        //
        Retry.decorateFunction(retry, o -> {
            log.info("执行：decorateFunction");

            return false;
        }).apply(Boolean.FALSE);

        // runable 无入参，无出参
        Retry.decorateRunnable(retry, () -> {
            log.info("执行：decorateRunnable");
            //  throw new RuntimeException();
        }).run();

        Retry.decorateCheckedRunnable(retry, () -> {
            log.info("执行：decorateCheckedRunnable");
            //throw new RuntimeException();
        }).unchecked().run();

        // callable 无入参,有出参
        Retry.decorateCallable(retry, () -> {
            log.info("执行：decorateCallable");
            return false;
        }).call();
        // 数据采集
        Consumer<Meter> meterConsumer = meter -> {
            //meter.getId().getTags().forEach(System.out::println);
            meter.use((gauge) -> {
                gauge.measure().forEach(m -> {
                    log.info(m.toString());
                });
            }, (vc -> {
                vc.measure().forEach(m -> {
                    log.info(m.toString());
                });
            }), (vt) -> {
                vt.measure().forEach(m -> {
                    log.info(m.toString());
                });
            }, (vs) -> {
                vs.measure().forEach(m -> {
                    log.info(m.toString());
                });
            }, (vltt) -> {
                vltt.measure().forEach(m -> {
                    log.info(m.toString());
                });
            }, (vtg) -> {
                vtg.measure().forEach(m -> {
                    log.info(m.toString());
                });
            }, (vfc) -> {
                vfc.measure().forEach(m -> {
                    log.info(m.toString());
                });
            }, (vft) -> {
                vft.measure().forEach(m -> {
                    log.info(m.toString());
                });
            }, vm -> {
                vm.measure().forEach(m -> {
                    log.info(m.toString());
                });
            });
            String desc = meter.getId().getDescription();
            String metricName = meter.getId().getTag("kind");
            Double metricValue = StreamSupport.stream(meter.measure().spliterator(), false)
                    .filter(m -> m.getStatistic().name().equals("COUNT"))
                    .findFirst()
                    .map(m -> m.getValue())
                    .orElse(0.0);
            //System.out.println(desc + " - " + metricName + ": " + metricValue);
        };
        meterRegistry.forEachMeter(meterConsumer);
    }
}
