package demojava.io.inputstream;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.stream.IntStream;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className DataInputStreamTest
 * @description TODO
 * @date 2023/9/25
 */
@Slf4j
public class DataInputStreamTest {
    @Test
    void testRead() throws IOException {
        File file = new File("D:\\t\\int.txt");
        file.delete();
        FileOutputStream fos = new FileOutputStream("D:\\t\\int.txt");
        DataOutputStream dos = new DataOutputStream(fos);
        IntStream.rangeClosed(0, 10).forEach(n -> {
            try {
                log.info("写入值为：{}", n);
                dos.write(n);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        dos.flush();
        dos.close();
        DataInputStream dts = new DataInputStream(new FileInputStream("D:\\t\\int.txt"));
        while (dts.read() != -1) {
            log.info("read value is {}", dts.read());
        }
        dts.close();

    }
}
