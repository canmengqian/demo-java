package demojava.io;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className FileDescriptorTest
 * @description TODO
 * @date 2023/9/22
 */
public class FileDescriptorTest {

    @Test
    /**
     * 测试标准输出
     */
    public void testOut() throws IOException {
        OutputStream op = new FileOutputStream(FileDescriptor.out);
        op.write("hello".getBytes());
        op.close();
    }

    @Test
    public void testIn() throws IOException {
        FileInputStream fi = new FileInputStream("D:\\t\\07\\FSD_103000000004_100_20230901_07.TXT");
        Reader reader = new FileReader(fi.getFD());
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String s = scanner.next();
            System.out.println(s);
        }
    }
}
