package com.example.demojava.nio;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className PathsTest
 * @description TODO
 * @date 2023/5/15
 */
public class PathsTest {
    @Test
    public void testPath() {
        // 创建文件路径,一个参数为绝对路径 ，两个入参第一个参数为绝对路径,第二个为相对路径
        Path path = Paths.get("D:\\t", "csv\\test.txt");
        System.out.println(path.toAbsolutePath().toString());
        // 获取文件名
        System.out.println("File name: " + path.getFileName());
        // 获取父目录
        System.out.println("Parent: " + path.getParent());
        // 获取根目录
        System.out.println("Root: " + path.getRoot());
        //TODO 将路径与另一个路径结合
        Path newPath = path.resolve("config/app.properties");
        System.out.println("Resolved path: " + newPath);
        // 将相对路径改为绝对路径
        Path path1 = Paths.get("D:\\t", "./csv\\test.txt");
        Path normalizedPath = path1.normalize();
        System.out.println("将相对路径改为绝对路径 Normalized path: " + normalizedPath);
        // 将相对路径转换为绝对路径
        Path absolutePath = path.toAbsolutePath();
        System.out.println("获取绝对路径: " + absolutePath);


        /*// PathMatcher
        FileSystems.getDefault().getPathMatcher("");

        // FileStore
        FileSystems.getDefault().getFileStores();

        DirectoryStream directoryStream = Files.newDirectoryStream("");*/


    }

    public void testFiles() throws IOException {
        // CopyOption
        Files.copy((InputStream) null, null, null);
        //Files.move()
       // Files.newDirectoryStream()
       // Files.createDirectory()

    }
}
