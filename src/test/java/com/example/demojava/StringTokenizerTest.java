package com.example.demojava;

import org.junit.jupiter.api.Test;

import java.util.StringTokenizer;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className StringTokenizerTest
 * @description TODO
 * @date 2023/5/10
 */
public class StringTokenizerTest {
    @Test
    public void test1() {
        String exp = "86|010|18745205107-1111";
        // 按照 |或-对输入的字符串进行提取
        StringTokenizer tokenizer = new StringTokenizer(exp, "|-");
        while (tokenizer.hasMoreElements()) {
            System.out.println(tokenizer.nextElement()
            );
        }
        while (tokenizer.hasMoreTokens()){
            System.out.println(tokenizer.nextToken());
        }
    }
}
