package com.example.demojava;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.StringJoiner;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className StringJoinerTest
 * @description TODO
 * @date 2023/5/10
 */
public class StringJoinerTest {
    @Test
    public void testJoin() {
        StringJoiner joiner = new StringJoiner(",", "[", "]");
        Arrays.asList("1", "2", "3").forEach(joiner::add);
        System.out.println(joiner);
    }

    @Test
    public void testMerge() {
        StringJoiner sj = new StringJoiner(",");
        sj.add("张三").add("李四").add("王五"); // 张三,李四,王五

        StringJoiner sj2 = new StringJoiner("-", "[", "]");
        sj2.add("赵六").add("田七").add("王八"); // [赵六,田七,王八]

        // 谁调用则合并时以谁为主
        // sj.merge(sj2);
        // System.out.println(sj); // 张三,李四,王五,赵六-田七-王八
        sj2.merge(sj);
        System.out.println(sj2); // [赵六-田七-王八-张三,李四,王五]
    }
}
