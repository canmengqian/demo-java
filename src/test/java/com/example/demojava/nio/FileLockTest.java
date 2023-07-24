package com.example.demojava.nio;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className FileLockTest
 * @description TODO
 * @date 2023/5/16
 */
class FileLockTest1 {
    @Test
    public void testLock() throws IOException {
        FileChannel channel = FileChannel.open(Paths.get("d:\\", "test.txt"), StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.APPEND);
        FileLock lock = channel.lock();
        ByteBuffer bf = ByteBuffer.wrap("hello".getBytes(StandardCharsets.UTF_8));
        channel.write(bf);
        System.out.println("是否共享锁:" + lock.isShared());
        System.out.println("是否有效锁:" + lock.isValid());
        lock.release();
    }
}

class FileLockTest2 {
    @Test
    public void testLock() {


    }
}