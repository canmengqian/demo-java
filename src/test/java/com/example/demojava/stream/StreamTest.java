package com.example.demojava.stream;

import cn.hutool.core.util.RandomUtil;
import com.example.demojava.dto.Person;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className StreamTest
 * @description TODO
 * @date 2023/9/6
 */
@Slf4j
public class StreamTest {
    @Test
    void test() {
        /**
         * 创建方式
         * 1. 范围创建 支持开区间和闭区间
         * 2. of 创建
         * 3. IntSupplier 方式创建,但是只能创建单一元素的流
         * 4. concat拼接两个流
         */
        /**
         * 操作方式
         * 1. 遍历，排序,过滤,map转换,计数,最大值,最小值,去重,跳过元素,平均值,求和
         * 2. 装箱,转换为其他流,转迭代器
         * 3. 条件取值,取出首元素,条件删除,限制取值个数,随机取值
         * 4. 并行流
         */
        System.out.println("开区间");
        IntStream.range(0, 5).forEach(System.out::println);
        System.out.println("闭区间");
        IntStream.rangeClosed(0, 4).forEach(System.out::println);
        System.out.println("of数组方式");
        IntStream.of(1, 2, 3, 4).forEach(System.out::println);
        System.out.println("of单元素方式");
        // 生成器方式,相当生产者,无线生产数据
        //  IntStream.generate(() -> 1).forEach(System.out::println);
        System.out.println("拼接方式");
        IntStream.concat(IntStream.of(1), IntStream.of(2)).forEach(System.out::println);
        // 操作方法
        IntStream.of(1, 2, 3, 4, 5).sorted().forEach(System.out::println);
        IntStream.of(1, 2, 3, 4, 5).sorted().filter(i -> i > 3).forEach(System.out::println);
        IntStream.of(1, 2, 3, 4, 5).map(i -> i * i).sorted();

    }

    @Test
    void test2() {
        int remain = 3;
        int count = 20;
        /*IntStream.rangeClosed(1, count)
                .skip( count-remain>0 ? count-remain : count  )
                .mapToObj(i -> String.valueOf(i * 2))
                .forEach(System.out::println);*/
        IntStream.concat(IntStream.rangeClosed(0, 5), IntStream.rangeClosed(6, 10)).skip(5)
                .dropWhile(value -> value == 5 || value == 6 || value == 7)
                .limit(3).forEach(System.out::println);
        /*for(int i=1;i<=10;i++){
            System.out.println(String.valueOf(i*2));
        }*/
    }

    @Test
    void test3() {
        ForkJoinPool forkJoinPool = new ForkJoinPool(10);
        forkJoinPool.submit(() -> Stream.iterate(0, integer -> {
            return RandomUtil.randomInt(10);
        }).limit(20).iterator().forEachRemaining(System.out::println));
    }

    /**
     * 测试查找匹配
     */
    void test4() {
        Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 0).sequential().filter(i -> {
            return i > 5;
        }).forEach(System.out::println);

    }

    @Test
    void testReduce() {
        int sum = Stream.of(1, 2, 3, 4).reduce(0, (s, i) -> {
            return s + i;
        }, (v1, v2) -> {
            System.out.println("combiner");
            return v1 + v2;
        }).intValue();
        System.out.println(sum);

        var users =
                IntStream.rangeClosed(1, 4).mapToObj(i -> {
                    return new Person("" + i, i);
                }).toList();
        Person newPerson = users.stream().reduce(new Person("lisi", 1), (s, p) -> new Person(p.getName(), s.getAge() + p.getAge()), (u1, u2) -> new Person("lisi", u1.getAge() * u2.getAge()));
        System.out.println(newPerson.toString());
        Person newPerson1 = users.stream().parallel().reduce(new Person("lisi", 1), (s, p) -> new Person(p.getName(), s.getAge() + p.getAge()), (u1, u2) -> new Person(u2.getName(), u1.getAge() * u2.getAge()));
        System.out.println(newPerson1.toString());
    }
}
