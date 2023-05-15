package com.example.demojava.nio;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.*;

import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className WatchServiceTest
 * @description TODO
 * @date 2023/5/15
 */
@Slf4j
public class WatchServiceTest {
    @Test
    public void testWatch() throws IOException {
        WatchService watchService = FileSystems.getDefault().newWatchService();
        // 初始化一个被监控文件夹的Path类
        Path path = Paths.get("d:\\t\\");
        // 将路径注册到监听服务中,监听指定的事件类型
        path.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE);
        // 注册应用服务关闭后的事件
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                System.out.println("关闭文件监测服务");
                watchService.close();
            } catch (IOException e) {

                log.error(e.getMessage());
            }
        }));

        WatchKey key;
        while (true) {
            try {
                key = watchService.take();
                System.out.println("key 是否有效：" + key.isValid());
                // 拉取事件
                for (WatchEvent<?> event : key.pollEvents()) {
                    Path file = (Path) event.context();
                            System.out.println("StandardWatchEventKinds: 文件事件类型" + event.kind().name());
                    System.out.println("文件名称" + file.toFile().getAbsolutePath());
                    System.out.println("文件个数" + event.count());
                }
                boolean reset = key.reset();
                if (!reset) {
                    log.info("该文件无法重置");
                    break;
                }
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
    }
}
