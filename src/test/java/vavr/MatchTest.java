package vavr;

import cn.hutool.core.util.RandomUtil;
import io.vavr.control.Option;
import org.junit.jupiter.api.Test;

import java.util.function.Predicate;

import static io.vavr.API.*;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className MatchTest
 * @description TODO
 * @date 2023/8/29
 */
public class MatchTest {
    @Test
    void test() {
        int i = RandomUtil.randomInt(10);
        String s = Match(i).of(
                Case($(1), "one"),
                Case($(2), "two"),
                Case($(i1 -> i1 >= 3 && i1 <= 7), "gt 3 and lt 7"),
                Case($(), "?")
        );
        System.out.println(i + "\t " + s);
    }

    @Test
    void testOption() {
        Option<Integer> option = Option(null);
        System.out.println(Option.none().equals(option));
        var str = Match(option).of(
                Case($(None()), "none"),
                Case($(Some(1)), option.get().toString()),
                Case($(t -> t.get() > 2 && t.get() < 10), "gt 3 and lt 10"),
                Case($(), "not match"));
        System.out.println(str);
    }
}
