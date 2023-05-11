package com.example.demojava.zip;

import cn.hutool.core.io.FileUtil;
import org.junit.jupiter.api.Test;
import org.mockito.internal.util.io.IOUtil;
import org.springframework.util.FileSystemUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Enumeration;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
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
            // 读取文件
            if (!e.isDirectory()) {
                InputStream is = zip.getInputStream(e);
                Collection<String> sts = IOUtil.readLines(is);
                sts.forEach(System.out::println);
            }
        }


    }

    @Test
    public void testGzip() throws IOException {
        byte[] compress = comparess();
        byte[] uncomparess = uncomparess(compress);
        System.out.println("解压缩后长度:" + uncomparess.length);
    }

    public byte[] comparess() throws IOException {
        byte[] ins = FileUtil.readBytes(new File("D:\\t\\test.txt.gz"));
        System.out.println("ins length:" + ins.length);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream bo = new ByteArrayInputStream(ins);
        GZIPInputStream gzip = new GZIPInputStream(bo);
        byte[] buffer = new byte[1];
        while (gzip.read(buffer) > 0) {
            out.write(buffer);
        }
        byte[] outs = out.toByteArray();
        System.out.println("outs length：" + outs.length);
        return outs;

    }

    public byte[] uncomparess(byte[] ins) throws IOException {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        GZIPOutputStream gzipOut = new GZIPOutputStream(bo);
        gzipOut.write(ins);
        return bo.toByteArray();
    }
}
