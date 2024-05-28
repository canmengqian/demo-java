package demojava.io;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.StringReader;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className LineNumberReaderTest
 * @description TODO
 * @date 2023/9/26
 */
public class LineNumberReaderTest {
    @Test
    void test() throws IOException {
        // 行处理
        LineNumberReader reader = new LineNumberReader(new StringReader("hello\r\nworld"));
        reader.lines().forEach(System.out::println);
        System.out.println(reader.markSupported() + "");

        // 重置到首行
        // 跳过第一行
        //  reader.skip(1);
        reader.lines().forEach(System.out::println);
    }
}
