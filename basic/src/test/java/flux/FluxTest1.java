package flux;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.RandomUtil;
import io.vavr.Function1;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.SignalType;
import reactor.core.scheduler.Schedulers;
import reactor.test.StepVerifier;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.logging.Level;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className FluxTest1
 * @description TODO
 * @date 2023/12/6
 */
@Slf4j
public class FluxTest1 {
    @Test
    void test() {
        //subscribe 可以指定Consumer , Subscriber,CoreSubscriber
        // blockFirst
        // all(),any() :转成Mono
        // buffer() 缓存数据并将其聚集成列表 , 1. 按个数 2.按条件 3.按时间
        // bufferTimeout() 按时间缓存
        // bufferUntil() 按推断条件缓存
        // just(),creatr(),generate(),
        /**
         * 生成方式
         */
        Flux.generate(sink -> {
            sink.next(RandomUtil.randomInt());
            sink.complete();
        }).subscribe(System.out::println);

        Flux.create(sink -> {
            for (int i = 0; i < 10; i++) {
                sink.next("create:" + i);
            }
            sink.complete();
        }).subscribe(System.out::println);
        Flux.just(1).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.println("接收到元素" + integer);
            }
        });
    }

    @Test
    void testRange() {
        Flux.range(1, 5).buffer(2).subscribe(ns -> {
            System.out.println(ns);
        });
        Flux.range(1, 10).bufferUntil(i -> i % 2 == 0).subscribe(System.out::println);
        Flux.range(1, 10).bufferWhile(i -> i % 2 == 0).subscribe(System.out::println);
    }

    @Test
    void testDelay() throws InterruptedException {
        // 延迟创建
        System.out.println("当前时间:" + LocalDateTime.now());
        Flux<LocalDateTime> flux1 = Flux.just(LocalDateTime.now());
        Flux<LocalDateTime> flux2 = Flux.defer(() -> Flux.just(LocalDateTime.now()));
        Thread.sleep(2000);
        flux1.subscribe(n -> System.out.println("flux1休眠两秒的时间，立即创建型" + n));
        flux2.subscribe(n -> System.out.println("flux2休眠两秒的时间，延迟创建型" + n));
    }

    @Test
    void testDelayElement() throws InterruptedException {
        // 对每个元素进行延时
        Flux.just(1, 2, 3).delayElements(Duration.ofSeconds(2)).subscribe(n -> {
            log.info("元素值:{},接收到时间:{}", n, LocalDateTime.now());
        });
        Thread.sleep(7000);
    }

    @Test
    void testWindow() throws InterruptedException {
        Flux.range(1, 100).window(5).subscribe(f -> f.take(1).subscribe(integer -> {
            System.out.println();
        }));
    }

    @Test
    void testZip() throws InterruptedException {
        Flux.just(1, 2).zipWith(Flux.just("1", "2")).subscribe(c -> {
            System.out.println(c);
        });
        // 通过函数进行合并
        Flux.just(1, 2).zipWith(Flux.just("1", "2"), (s1, s2) -> {
            return s1.toString() + "-" + s2;
        }).subscribe(c -> {
            System.out.println(c);
        });
    }

    @Test
    void testTake() {
        // 取出前两个
        Flux.range(1, 1000).take(2).subscribe(n -> {
            System.out.println(n);
        });

        Flux.range(1, 1000).takeLast(2).subscribe(n -> {
            System.out.println(n);
        }, e -> {
            System.out.println(e);
        }, () -> {
            System.out.println("处理完成");
        });
    }

    @Test
    void testMerge() {
        Flux.just(1, 2).mergeWith(Flux.just(3, 4)).subscribe(n -> {
            System.out.println(n);
        });

        Flux.merge(Flux.range(1, 2), Flux.range(3, 4)).subscribe(n -> {
            System.out.println(n);
        });
    }

    @Test
    public void testFlatMap() {
        // 将元素转为Flux
        Flux.range(1, 5).flatMap((i) -> Flux.just(i)).subscribe(n -> System.out.println(n));
    }

    @Test
    void testcombineLatest() {
        Flux.combineLatest(
                Arrays::toString,
                Flux.interval(Duration.ofSeconds(1)).take(5),
                Flux.interval(Duration.ofSeconds(1)).take(5)
        ).toStream().forEach(System.out::println);

    }

    @Test
    void testswitchOnError() {
        // 出错时return 默认值
        Flux.concat(Mono.error(new IllegalArgumentException()))
                .onErrorReturn(RandomUtil.randomInt(10))
                .subscribe(System.out::println);
        // 根据异常类型返回值,但是只返回首次错误
        Flux.concat(Mono.error(new IllegalArgumentException()))
                .concatWith(Flux.error(new ClassNotFoundException()))
                .onErrorReturn(IllegalArgumentException.class, 1)
                .onErrorReturn(ClassNotFoundException.class, 2)
                .subscribe(System.out::println);

        Flux.concat(Mono.error(new IllegalArgumentException()))
                .concatWith(Flux.error(new ClassNotFoundException()))
                .onErrorContinue(IllegalArgumentException.class, (e, o) -> {
                    System.out.println("出错误了");
                })
                .onErrorReturn(ClassNotFoundException.class, 2)
                .subscribe(System.out::println);

    }

    @Test
    void testReturn() {
        Flux.just(1, 2, 3)
                .publishOn(Schedulers.boundedElastic())
                .map(n -> {
                    System.out.println("当前线程:" + Thread.currentThread().getName());
                    return n * 2;
                })
                .map(n -> {
                    System.out.println("当前线程:" + Thread.currentThread().getName());
                    return n * 2;
                })
                .subscribeOn(Schedulers.single())
                .subscribe(integer -> {
                    System.out.println("当前线程1:" + Thread.currentThread().getName());
                    System.out.println(integer);
                });

        Flux.create(sink -> {
                    sink.next(Thread.currentThread().getName());
                    sink.complete();
                })
                .publishOn(Schedulers.single())
                .map(x -> String.format("[%s] %s", Thread.currentThread().getName(), x))
                .publishOn(Schedulers.boundedElastic())
                .map(x -> String.format("[%s] %s", Thread.currentThread().getName(), x))
                .subscribeOn(Schedulers.parallel())
                .toStream()
                .forEach(System.out::println);
    }

    @Test
    void testVerify() {
        StepVerifier.create(Flux.just(1, 2))
                .expectNext(1)
                .expectNext(2)
                .verifyComplete();
    }

    @Test
    void testCheckpoint() {
        log.info("不使用检查点");
        Flux.just(1).map(n -> n / 0).subscribe(System.out::println);
        log.info("使用检查点");
        Flux.just(1).map(n -> n / 0).checkpoint("map").subscribe(System.out::println);
    }

    @Test
    void testLog() {
        Flux.range(0, 2).log("range", Level.INFO, SignalType.REQUEST).subscribe(System.out::println);
    }

    /**
     * 转换
     */
    @Test
    void transform() {
        // 转换为其他flux类型
        Flux.just(1).transform(new Function<Flux<Integer>, Publisher<String>>() {
            @Override
            public Publisher<String> apply(Flux<Integer> integerFlux) {
                return Flux.just("" + integerFlux.take(1));
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s.toString());
            }
        });
    }

    @Test
    void draft() {
        log.info("then方法使用，流程编排");
        List<Integer> rs = new ArrayList<>();
        Object obj = Flux.just(1)
                .map(n -> {
                    log.info("第一个flux：{}", n);
                    return n;
                })
                .collectList()
                .doOnNext(rs::addAll)
                .thenMany(Flux.just(2, 3))
                .map((n) -> {
                    log.info("map：{}", n);
                    return n * 2;
                })
                .doOnNext(rs::add)
                .then(Mono.just(1)).block();
        System.out.println(obj);
        System.out.println(rs.toString());

        log.info("using");
        // 第二个参数使用Flux来接收callbale 的数据,作为callable的数据容器
        // using中的第三个参数指的是订阅callbale产生的数据，
        // subscribe是等待map完成之后可以进行订阅消费,跟using里的consumer使用场景不一样
        Flux.using((Callable<List<Integer>>) () -> CollUtil.newArrayList(1, 2, 3), Flux::just, System.out::println)
                .window(10).doOnNext(f -> {
                    System.out.println(f.collectList());
                })
                .map(n -> "hello" + n).subscribe(System.out::println);
        Flux.just(1, 2, 3, 4).elapsed().subscribe(t -> {
            System.out.println("t1:" + t.getT1() + "t2:" + t.getT2());
        });
    }

    void conditionRetry() {

    }

    @Test
    void testNull() {

    }
}
