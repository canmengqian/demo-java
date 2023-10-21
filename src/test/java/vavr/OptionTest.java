package vavr;

import io.vavr.Function1;
import io.vavr.control.Option;
import org.junit.jupiter.api.Test;

import java.util.function.Supplier;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className OptionTest
 * @description TODO
 * @date 2023/8/29
 */
public class OptionTest {
    @Test
    void test() {
        Option<String> maybeFoo = Option.of("foo").orElse (new Supplier<Option<? extends String>> () {
            @Override
            public Option<? extends String> get() {
                return Option.of ("1");
            }
        });
        //then(maybeFoo.get()).isEqualTo("foo");
        Option<String> maybeFooBar = maybeFoo
                .flatMap((Function1<String, Option<String>>) s -> Option.of(s.toUpperCase()))
                //.flatMap(s -> Option.of((String) null))
                .map(s -> {
                    String newVal = s.toUpperCase() + "bar";
                    System.out.println("new val is null");
                    return newVal;
                });
        System.out.println(maybeFooBar.isEmpty());
        //then(maybeFooBar.isEmpty()).isTrue();
    }
}
