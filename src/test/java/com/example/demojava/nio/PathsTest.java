package com.example.demojava.nio;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.atomic.AtomicInteger;

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

    /**
     * @return
     * @Param
     * @author zhengzz
     * @description TODO
     * @date 18:10 2023/5/15
     * @see https://blog.csdn.net/u010889616/article/details/52694061
     **/
    @Test
    public void testPath2() throws IOException {
        // 相对路径
        Path p1 = Paths.get("d:/", "t/test.txt");
        System.out.println("p1" + p1.toAbsolutePath().toString());
        // 绝对路径
        Path p2 = Paths.get("d:/t/test.txt");
        System.out.println("p2" + p1.toAbsolutePath().toString());
        // uri模式
        URI u = URI.create("file:///d:/t/test.txt");
        Path p3 = Paths.get(u);
        System.out.println("p3" + p1.toAbsolutePath().toString());

        // FileSystems构造：
        Path p4 = FileSystems.getDefault().getPath("d:/", "t/test.txt");
        System.out.println("p4" + p1.toAbsolutePath().toString());

        // File和Path之间的转换，
        File f = p1.toFile();
        System.out.println("Path 转 File :" + f.getAbsolutePath().toString());
        System.out.println("File 转 URI :" + f.toURI().toString());
        System.out.println("File 转 Path :" + f.toPath().toString());

        System.out.println("目录遍历");
        AtomicInteger i1 = new AtomicInteger(0);
        Files.newDirectoryStream(Paths.get("d:\\t")).forEach(p -> {
            //  System.out.println(p.toString());
            i1.incrementAndGet();
        });
        System.out.println("newDirectoryStream模式遍历文件" + i1.get() + "个");

        i1.set(0);
        // 遍历所有目录
        Files.walkFileTree(Paths.get("d:\\t"), new FileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                return null;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                return null;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                return null;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                return null;
            }
        });
    }

    public void testFiles() throws IOException {
        // CopyOption
        Files.copy((InputStream) null, null, null);
        //Files.move()
        // Files.newDirectoryStream()
        // Files.createDirectory()

    }
}
