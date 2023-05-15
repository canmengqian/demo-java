package com.example.demojava.nio;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className FileVisitorTest
 * @description TODO
 * @date 2023/5/15
 */
public class FileVisitorTest {
    /**
     * @return
     * @Param
     * @author zhengzz
     * @description 递归获取某个目录下的文件信息
     * @date 18:01 2023/5/15
     **/
    @Test
    public void visitor() throws IOException {
        Files.walkFileTree(Paths.get("d:\\", "t"), new FileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                System.out.println("访问目录前的操作：" + dir.toAbsolutePath().toString());
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                System.out.println("正在访问文件：" + file.toAbsolutePath().toString());
                System.out.println("是否为目录:" + attrs.isDirectory());
                System.out.println("创建时间:" + attrs.creationTime());
                System.out.println("是否正常文件:" + attrs.isRegularFile());
                System.out.println("文件大小:" + attrs.size());
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                return FileVisitResult.CONTINUE;
            }
        });
    }
}
