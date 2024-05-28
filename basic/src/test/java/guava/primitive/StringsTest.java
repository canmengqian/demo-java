package guava.primitive;

import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class StringsTest {
    @Test
    void test() {
        String cp = Strings.commonPrefix ("a", "b");
        log.info (cp);
        String cf = Strings.commonSuffix ("a", "b");
        log.info (cf);
        log.info (Strings.emptyToNull (""));
        log.info ("指定字符串重复N次，{}",Strings.repeat ("A", 5));
    }
}
