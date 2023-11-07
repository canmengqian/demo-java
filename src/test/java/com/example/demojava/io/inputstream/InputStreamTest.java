package com.example.demojava.io.inputstream;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringBufferInputStream;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className InputStreamTest
 * @description TODO
 * @date 2023/9/26
 */
@Slf4j
public class InputStreamTest {
    @Test
    void testStringBufferInputStream() throws IOException {
        StringBufferInputStream stream = new StringBufferInputStream("hello");
        byte[] bs = stream.readAllBytes();
        log.info("读取到的字节数据为:{}", new String(bs));
    }
}
