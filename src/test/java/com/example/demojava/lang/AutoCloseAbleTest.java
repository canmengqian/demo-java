package com.example.demojava.lang;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className AutoCloseAbleTest
 * @description TODO
 * @date 2023/8/30
 */
@Slf4j
public class AutoCloseAbleTest implements AutoCloseable {
    void test() {

    }

    @Override
    public void close() throws Exception {
        log.info("close");
    }
}
