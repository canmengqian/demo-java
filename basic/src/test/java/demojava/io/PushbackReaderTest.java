package demojava.io;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.PushbackReader;
import java.io.Reader;
import java.io.StringReader;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className PushbackReaderTest
 * @description TODO
 * @date 2023/9/26
 */
@Slf4j
public class PushbackReaderTest {
    @Test
    void test() throws IOException {
        PushbackReader reader = new PushbackReader(new StringReader("hello world"), 10);
        char[] cbf = new char[1];
        while (reader.read(cbf) != -1) {
            // log.info("读取到的数据为:{}", String.valueOf(cbf));
            if (String.valueOf(cbf).contains("C")) {
                break;
            }
            reader.unread('C');
        }
        System.out.println(""+reader.markSupported());
    }
}
