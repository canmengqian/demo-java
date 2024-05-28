package demojava.bean;

import demojava.dto.Person;
import org.junit.jupiter.api.Test;

import java.beans.BeanDescriptor;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.text.MessageFormat;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className BeanInfoTest
 * @description TODO
 * @date 2023/5/30
 */
public class BeanInfoTest {
    @Test
    public void test() throws IntrospectionException {
        BeanInfo beanInfo = Introspector.getBeanInfo(Person.class);
        // 获取bean描述器
        BeanDescriptor bd = beanInfo.getBeanDescriptor();
        String desc = MessageFormat.format("{0},{1},{2},{3},{4}", bd.getName(), bd.getDisplayName(), bd.isExpert(),
                bd.isHidden(), bd.isPreferred());
        System.out.println(desc);
    }
}
