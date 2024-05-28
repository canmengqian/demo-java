package demojava.managerment;

import org.junit.jupiter.api.Test;

import javax.management.MBeanServer;
import java.lang.management.ClassLoadingMXBean;
import java.lang.management.ManagementFactory;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className ManagementFactoryTest
 * @description TODO
 * @date 2023/5/12
 */
public class ManagementFactoryTest {
    @Test
    public void testMxBean() {

        ClassLoadingMXBean classLoadingMXBean = ManagementFactory.getClassLoadingMXBean();
        System.out.println("获取已加载类的数量:" + classLoadingMXBean.getLoadedClassCount());
        System.out.println("获取总加载类的数量:" + classLoadingMXBean.getTotalLoadedClassCount());
        System.out.println("对象名称:" + classLoadingMXBean.getObjectName());
        System.out.println("获取卸载类的数量:" + classLoadingMXBean.getUnloadedClassCount());
    }
}
