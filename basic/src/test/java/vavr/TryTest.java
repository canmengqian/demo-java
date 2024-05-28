package vavr;

import io.vavr.CheckedFunction0;
import io.vavr.Function1;
import io.vavr.PartialFunction;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.ExceptionUtils;

import java.util.function.Consumer;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className TryTest
 * @description 1. 完成try...catch...流程
 * 2. 完成map,transform,peek,filter等转换功能
 * 3.完成java 标准库的转换方式 List,map ,set,Array,Queue
 * 4. 完成Either,Option转换功能
 * 5. 完成success，fail事件功能
 * @date 2023/8/29
 */
@Slf4j
public class TryTest {
    @Test
    void test() throws Throwable {
        var val = Try.of((CheckedFunction0<Integer>) () -> {
            log.info("初始方法，{}", 1);
            var nu = 1 / 0;
            return nu;
        }).onSuccess(c -> {
            log.info("执行成功，{}", c.toString());
        }).onFailure(c -> {
            log.info("执行失败，抛出的异常为：{}");
        }).recover((e) -> {
            log.info("抛出异常后可以从中进行设置默认值:{}", ExceptionUtils.readStackTrace(e));
            return 2;
        }).andThen(integer -> {
            int i = 3 + integer;
            log.info("执行consumer1结果:{}", i);
        }).andThen(c -> {
            int i = 4 + c;
            log.info("执行consumer2结果:{}", i);
        }).andFinally(() -> {
            log.info("执行finally方法");
        }).map(m -> {
            log.info("重新进行map计算");
            return 2 + m;
        }).getOrElseThrow(new Function1<Throwable, Throwable>() {
            @Override
            public Throwable apply(Throwable throwable) {
                return new IllegalArgumentException();
            }
        });

        System.out.println("执行try之后的结果" + val);
    }

    @Test
    void testThrow() throws Throwable {
        // 想要抛出异常不能使用recover方法
        var val = Try.of((CheckedFunction0<Integer>) () -> {
            log.info("初始方法，{}", 1);
            return 1 / 0;
        }).onSuccess(c -> {
            log.info("执行成功，{}", c.toString());
        }).onFailure(c -> {
            log.info("执行失败，抛出的异常为：{}");
        }).andThen(integer -> {
            int i = 3 + integer;
            log.info("执行consumer1结果:{}", i);
        }).andThen(c -> {
            int i = 4 + c;
            log.info("执行consumer2结果:{}", i);
        }).andFinally(() -> {
            log.info("执行finally方法");
        }).map(m -> {
            log.info("重新进行map计算");
            return 2 + m;
        }).getOrElseThrow((Function1<Throwable, Throwable>) throwable -> new IllegalArgumentException());

        System.out.println("执行try之后的结果" + val);
    }
}
