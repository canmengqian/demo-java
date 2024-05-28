package vavr;

import cn.hutool.core.util.RandomUtil;
import io.vavr.CheckedFunction0;
import io.vavr.concurrent.Future;
import io.vavr.concurrent.Promise;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.ExceptionUtils;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className PromiseTest
 * @description TODO
 * @date 2023/8/30
 */
@Slf4j
public class PromiseTest {
    @Test
    void test() {
        /**
         *1. 创建异步操作 of系列,可以指定线程池，提供function,callable,suppplier方式进行异步处理
         *2.常规操作
         * future.isAsync() 是否异步操作
         *3. 实现 complete，fail，sucess事件的监听操作
         * 4. 实现map,filter操作,并且这些操作可以多次交叉迭代按顺序执行，即 map->filter->map->map->filter
         * 5. 实现了onNext下一步操作
         * 6. 实现异常恢复操作,可以指定异常类型
         */
        Future future = Future.of((CheckedFunction0<Boolean>) () -> true);
        log.info("是否异步:{},是否为空{},是否取消{},是否完成：{},是否失败：{},是否成功：{},是否懒执行:{},是否单值：{}",
                future.isAsync(),
                future.isEmpty(),
                future.isCancelled(),
                future.isCompleted(),
                future.isFailure(),
                future.isSuccess(),
                future.isLazy(),
                future.isSingleValued()
        );
        System.out.println("是否异步" + future.isAsync());
        System.out.println("future执行结果:" + future.get());
        for (int i = 0; i < 10; i++) {

            log.info("正在执行第{}次循环", i);
            Future<Integer> f1 = Future.of((CheckedFunction0<Integer>) () -> {
                int num = RandomUtil.randomInt(10);
                log.info("函数计算产生的值为{}", num);
                if (num == 0) {
                    throw new IllegalStateException();
                }
                if (num <= 5) {
                    throw new IllegalArgumentException();
                }
                return num;
            });
            Integer val = f1.onComplete(c -> {
                        log.info("该操作已经完成，{}", c);
                    }).onSuccess(c -> log.info("该操作成功：{}", c))
                    .onFailure(c -> log.info("该操作已经失败，{}", ExceptionUtils.readStackTrace(c)))
                    .map(c -> {
                        log.info("map操作");
                        return c * 10;
                    }).filter(p -> {
                        if (p > 800) {
                            log.info("过滤大于800的值");
                            return false;
                        }
                        return true;
                    }).andThen(c -> {
                        log.info("下一步操作,前一步操作是否成功:{}", c.isSuccess());
                        log.info("下一步消费者操作,当前值：{}", c);
                    }).recover(IllegalStateException.class, n -> {
                        log.info("发生IllegalStateException异常并进行恢复，设置默认值");
                        return 0;
                    }).recover(IllegalArgumentException.class, n -> {
                        log.info("发生IllegalArgumentException异常并进行恢复，设置默认值");
                        return 5;
                    }).get();
            System.out.println(val);
        }
    }

    @Test
    void testTrans() {
        Future<Integer> f1 = Future.of(() -> RandomUtil.randomInt(10));
        f1.toCharSeq().forEach(System.out::println);
        f1.toJavaList().forEach(System.out::println);
        f1.toJavaSet().forEach(System.out::println);
        f1.toJavaStream().forEach(System.out::println);
        f1.stderr();
        System.out.println(f1.toJavaOptional().get());
        log.info("to try:{}", f1.toTry().isSuccess());


    }

    @Test
    void testPromise() {
        Future<Integer> f1 = Promise.fromTry(Executors.newSingleThreadExecutor(), Try.ofCallable(() -> {
            int num = RandomUtil.randomInt(10);
            if (num < 9) {
                throw new IllegalStateException();
            }
            return num;
        })).future();

        log.info("{},{}", f1.isAsync(), f1.get());
    }
}
