package com.example.demojava.io.reader;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.CharBuffer;

@Slf4j
public class StringReaderTest {
    @Test
    public  void test() throws IOException {
        Reader reader = new StringReader ("hello");
       boolean ready= reader.ready ();
       log.info ("reader 是否准备好:{}",ready);
       // CharBuffer buffer=CharBuffer.allocate (10);
        char[] chars = new char[10];
        while (reader.read ()!=-1){
           reader.read (chars);
           log.info ("读取到的内容为:{}",String.valueOf (chars));
       }
        reader.reset ();
        log.info ("读取到的内容为:{}",String.valueOf (chars));
        // 将读取到的内容传输到标准输出中
        Writer writer = new StringWriter (10);
        reader.transferTo (writer);

        writer.flush ();
        writer.close ();
        log.info ("outStr "+ writer);
    }
}
