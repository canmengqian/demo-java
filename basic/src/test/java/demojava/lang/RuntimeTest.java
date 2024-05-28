package demojava.lang;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className RuntimeTest
 * @description
 * 1. 完成指定环境下，指定目录的命令或脚本调用 并返回对应的输入输出流
 * 2. 统计当前虚拟机的内存使用量
 * 3. 添加shutdown钩子线程，虚拟机推出时执行该线程
 * 4. 执行GC
 * 5. 加载外部dll三方库
 * @date 2023/8/31
 */
@Slf4j
public class RuntimeTest {
    @Test
    void test() throws IOException {
        Runtime runtime = Runtime.getRuntime();
        runtime.exec("notepad");
        runtime.addShutdownHook(new Thread(() -> System.out.println("shutdowm")));
        log.info("可用的CPU核心数:{},总使用内存：{},最大内存:{},剩余内存：{}", runtime.availableProcessors(), runtime.totalMemory() / 1024 / 1024, runtime.maxMemory() / 1024 / 1024, runtime.freeMemory() / 1024 / 1024);
        runtime.gc();
        log.info("可用的CPU核心数:{},总使用内存：{},最大内存:{},剩余内存：{}", runtime.availableProcessors(), runtime.totalMemory() / 1024 / 1024, runtime.maxMemory() / 1024 / 1024, runtime.freeMemory() / 1024 / 1024);
    }
}
