package demojava.function;

import cn.hutool.core.lang.Snowflake;
import org.junit.jupiter.api.Test;

import java.util.function.Supplier;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className SupplierTest
 * @description TODO
 * @date 2023/5/11
 */
public class SupplierTest {
    Snowflake snowflake = new Snowflake(1);

    @Test
    public void testGet() {
        // 作为生产者，无法接收外界的输入，只能自己生产数据并返回结果
        Supplier<Integer> s1 = () -> 1 + 1;
        System.out.println(s1.get());
        Supplier<String> s2 = () -> snowflake.nextIdStr();
        for (int i = 0; i < 10; i++) {
            System.out.println(s2.get());
        }
    }
}
