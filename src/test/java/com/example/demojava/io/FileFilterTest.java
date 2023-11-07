package com.example.demojava.io;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileFilter;
import java.util.Locale;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className FileFilterTest
 * @description TODO
 * @date 2023/9/26
 */
@Slf4j
public class FileFilterTest {
    FileFilter fileFilter = path -> {
        if (path.isDirectory()) {
            return false;
        }
        if (path.getName().toLowerCase(Locale.ROOT).contains("txt")) {
            return true;
        }
        return false;
    };

    @Test
    void test() {
        log.info("使用文件过滤器之前");
        File[] files1 = new File("d:\\t").listFiles();
        for (File f : files1) {
            System.out.println(f.getAbsolutePath());
        }
        log.info("使用文件过滤器之后");
        File[] files = new File("d:\\t").listFiles(fileFilter);
        for (File f : files) {
            System.out.println(f.getAbsolutePath());
        }
    }
}
