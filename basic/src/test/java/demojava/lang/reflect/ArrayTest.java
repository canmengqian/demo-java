package demojava.lang.reflect;


import bean.JaPerson;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className ArrayTest
 * @description TODO
 * @date 2023/9/5
 */
@Slf4j
public class ArrayTest {
    @Test
    void test() {
        int[] ints = (int[]) Array.newInstance(int.class, 10);
        Array.set(ints, 0, 1);
        System.out.println(Array.getInt(ints, 0));
        Arrays.stream(ints).forEach(System.out::println);

        JaPerson[] ps = (JaPerson[]) Array.newInstance(getClazz(new JaPerson()), 10);
        Array.set(ps, 0, new JaPerson("李四"));
        log.info("人物信息:{}", Array.get(ps, 0).toString());
    }

    /**
     * 测试Number类型
     */
    @Test
    void testNumber(){
        // 放置Number类型的数据
       Number[] ns = (Number[]) Array.newInstance(Number.class,2);
       Array.set(ns,0,1);
       Array.set(ns,1,2.0D);
        Arrays.stream(ns).forEach(n->{
            System.out.println(n.getClass().getName());
        });
    }

    public static <T> Class<T> getClazz(T t) {
        return (Class<T>) t.getClass();
    }
}
