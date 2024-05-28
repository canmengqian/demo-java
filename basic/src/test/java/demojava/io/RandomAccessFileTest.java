package demojava.io;

import org.junit.jupiter.api.Test;
import org.mockito.internal.util.io.IOUtil;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * 1. 获取文件描述符， 获取文件管道,获取文件指针
 * 2. 获取文件长度,按字节数组,字符，指定格式读取文件，通过字节数组,字符等当时写文件
 * 3. 定位到指定位置
 * 4. 跳过文件中指定的字节数
 *
 * @author zhengzz
 * @version 1.0.0
 * @className RandomAccessFileTest
 * @description TODO
 * @date 2023/9/25
 */
public class RandomAccessFileTest {
    /**
     * @return
     * @Param
     * @author zhengzz
     * @description 指定位置进行写入
     * @date 9:52 2023/9/25
     **/
    @Test
    public void testWrite() throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("D:\\t\\07\\test2.txt", "rw");
        // 光标指向文件起始头部
        randomAccessFile.seek(0);
        randomAccessFile.write("1234567890".getBytes());
        // 获取文件大小
        long size = randomAccessFile.length();
        // 文件光标指向文件末尾
        randomAccessFile.seek(size);
        randomAccessFile.write("1234567890".getBytes());
        size = randomAccessFile.length();
        // 指向光标中间
   /*     randomAccessFile.seek(size / 2);
        randomAccessFile.write("hello tom".getBytes());*/
        randomAccessFile.close();
    }

    /**
     * @return
     * @Param
     * @author zhengzz
     * @description 指定位置进行读取数据
     * @date 9:52 2023/9/25
     **/
    @Test
    public void testRead() throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("D:\\t\\07\\test2.txt", "r");
        // 从第十个位置开始读取
        randomAccessFile.seek(10);
        // 计算文件剩余内容,设置合理大小的字节容器
        byte[] rbs = new byte[(int) (randomAccessFile.length() - 10)];
        // 读取内容到容器里
        randomAccessFile.read(rbs);
        System.out.println(new String(rbs));

    }
}
