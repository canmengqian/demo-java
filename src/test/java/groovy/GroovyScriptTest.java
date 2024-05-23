package groovy;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyCodeSource;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className GroovyScriptTest
 * @description TODO
 * @date 2024/5/22
 */

public class GroovyScriptTest {
    @Test
    void test() throws InstantiationException, IllegalAccessException, IOException {
        GroovyClassLoader classLoader = new GroovyClassLoader();
        URL url =GroovyScriptTest.class.getResource("/groovy/Demo.class");
        GroovyCodeSource source = new GroovyCodeSource(new File("D:\\demo-java\\src\\test\\java\\groovy\\Demo.groovy"));
        Class<Demo> demo = classLoader.parseClass(source);
        demo.newInstance().sayHello();
    }
}
