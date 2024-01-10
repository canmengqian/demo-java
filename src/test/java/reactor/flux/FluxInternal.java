package reactor.flux;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className FluxInternal
 * @description TODO
 * @date 2023/12/28
 */
public class FluxInternal {
    @Test
    void testMultiSubcrible() throws InterruptedException {
        Flux<Long> flux = Flux.interval(Duration.ofSeconds(2));
        flux.subscribe(n -> {
            System.out.println("1号" + n);
        });
        Thread.sleep(3000);
        flux.subscribe(n -> {
            System.out.println("2号" + n);
        });
        Thread.currentThread().join();
    }

    @Test
    void testExpand() {
        AtomicInteger nums = new AtomicInteger();
        Flux.just(
                        2
                )// 展开两次
                .expand(integer -> {
                    if (nums.get() < 10) {
                        System.out.println("展开层级:父级" + integer + "子级" + nums.get());
                        Flux<Integer> f = Flux.just(nums.get());
                        nums.getAndIncrement();
                        return f;
                    }
                    return Flux.empty();
                })
                .map(integer -> "").subscribe(System.out::println);
    }


}
