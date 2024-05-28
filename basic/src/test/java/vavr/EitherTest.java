package vavr;

import io.vavr.control.Either;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className EitherTest
 * @description TODO
 * @date 2023/8/29
 */
@Slf4j
public class EitherTest {
    @Test
    void test() {
        Either<Throwable, Integer> left = Either.left(new IllegalArgumentException());
        log.info("is left：{}, is right:{}", left.isLeft(), left.isRight());
        Either<Throwable, Integer> right = Either.right(1);
        log.info("is left：{}, is right:{}", right.isLeft(), right.isRight());
    }
}
