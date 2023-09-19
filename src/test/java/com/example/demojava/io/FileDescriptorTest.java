package com.example.demojava.io;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.function.IntSupplier;
import java.util.function.IntUnaryOperator;
import java.util.function.LongBinaryOperator;
import java.util.stream.IntStream;

@Slf4j
public class FileDescriptorTest {
    @Test
    void test() throws IOException {
        // 写出到标准错误显示上
        OutputStream os = new FileOutputStream (FileDescriptor.err);
        os.write (new String("hello").getBytes ());
        os.close ();
    }

    @Test
    void  test2(){
        IntStream.rangeClosed (0,10);
     /*Long nums=   IntStream.rangeClosed (0,10).parallel ()
             .filter (i->i>5)
             .mapToLong (operand -> operand ).sorted ().reduce ();
        log.info ("结果集为{}",nums);*/
    }
}
