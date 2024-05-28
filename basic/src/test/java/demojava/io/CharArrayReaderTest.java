package demojava.io;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.CharArrayReader;
import java.io.IOException;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className CharArrayReaderTest
 * @description TODO
 * @date 2023/9/26
 */
@Slf4j
public class CharArrayReaderTest {

    @Test
    void  test() throws IOException {
        CharArrayReader  reader = new CharArrayReader("hello world".toCharArray());
        char[] ca = new char[2];
        while (reader.read(ca)!=-1){
            log.info("读取到的内容为{}",String.valueOf(ca));
        }
        reader.close();
    }
}
