package flux;

import demojava.dto.Person;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.math.BigDecimal;
import java.util.function.Function;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className FluxCastTest
 * @description 测试类型转化功能
 * @date 2023/12/29
 */
public class FluxCastTest {
    @Test
    void testAs() {
        Integer n1 = Flux.just(1, 2)
                .as(f -> f.map(n -> n + 3).reduce(Integer::sum))
                .block();
        System.out.println(n1);
        Flux.just(1)
                .cast(BigDecimal.class)
                .toStream()
                .forEach(b -> {
                    System.out.println(b);
                });
    }

    Function<Flux<Integer>, Flux<Person>> PERSON_TRANS_FT = (s) -> s
            .subscribeOn(Schedulers.newParallel("sub1", 4, true), false)
            .map(n -> {
                try {
                    Thread.currentThread().sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("当前线程" + Thread.currentThread().getName() + Thread.currentThread().getId());
                return new Person(n + "", 30);
            })
            .subscribeOn(Schedulers.newParallel("sub", 4, true), false)
            .publishOn(Schedulers.newParallel("pub", 4), 4);

    @Test
    void testTransform() throws InterruptedException {
        Flux.range(1, 4)
                .transform(PERSON_TRANS_FT)
                .metrics()
                .subscribe(p -> {
                    System.out.println(p.toString());
                    System.out.println("当前线程" + Thread.currentThread().getName() + Thread.currentThread().getId());
                });
        Thread.currentThread().join(10000);
    }
}
