package com.example.demojava.zip;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className ZipFileTest
 * @description TODO
 * @date 2023/5/10
 */
public class ZipFileTest {
    @Test
    public void testReadZip() throws IOException {
        ZipFile zip = new ZipFile("D:\\t\\zip.zip");
        System.out.println("name" + zip.getName());
        // 列出所有文件
        Enumeration<? extends ZipEntry> zipEs = zip.entries();
        while (zipEs.hasMoreElements()) {
            ZipEntry e = zipEs.nextElement();

            System.out.println("是否目录：" + e.isDirectory() + "名称:" + e.getName()
                    + "压缩后大小:" + e.getCompressedSize()
                    + "压缩前大小：" + e.getSize()
                    + "CRC:" + e.getCrc()
                    + "注释:" + e.getComment()
                    + "创建时间" + e.getCreationTime()
            );
        }
        // 读取文件

    }
}
