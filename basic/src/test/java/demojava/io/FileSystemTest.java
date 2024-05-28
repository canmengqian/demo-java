package demojava.io;


import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className FileSystemTest
 * @description TODO
 * @date 2023/9/6
 */
public class FileSystemTest {
    void test() throws IOException {
        FileSystems.newFileSystem(Paths.get(""));
    }
}
