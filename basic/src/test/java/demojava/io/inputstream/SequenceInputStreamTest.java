package demojava.io.inputstream;

import io.vavr.collection.List;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Vector;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className SequenceInputStreamTest
 * @description TODO
 * @date 2023/9/6
 */
public class SequenceInputStreamTest {
    @Test
    void test() throws IOException {
        Vector<InputStream> vector = new Vector<>();
        // 合并多个输入流，按1个流进行输出
        vector.add(new FileInputStream(new File("D:\\t\\test.txt")));
        vector.add(new FileInputStream(new File("D:\\t\\test.txt")));
        vector.add(new FileInputStream(new File("D:\\t\\test.txt")));
        Enumeration<InputStream> ens = vector.elements();
        InputStream is = new SequenceInputStream(ens);

        while (is.available() != 0) {
            byte[] allBs = is.readAllBytes();
            System.out.println(new String(allBs));
        }

    }
}
