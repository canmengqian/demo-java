package demojava.lang.reflect;

import java.lang.reflect.Field;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className ParameterTest
 * @description TODO
 * @date 2023/9/11
 */
public class FieldTest {
    void test() {
        Field[] fields = this.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            field.trySetAccessible();
            field.getDeclaringClass();
        }
    }
}
