package quartz.job.scheduler;

import org.junit.jupiter.api.Test;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className StdSchedulerFactoryTest
 * @description TODO
 * @date 2024/4/9
 */
public class StdSchedulerFactoryTest {
    @Test
    public void createDefaultScheduler() throws Exception {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.start();
        scheduler.shutdown();
    }

    @Test
    public void createWithDefaultConfig() throws Exception {
        SchedulerFactory factory = new StdSchedulerFactory();
       Scheduler scheduler= factory.getScheduler();
       scheduler.start();
       // 即使存在正在执行的作业也会被强制停止
       scheduler.shutdown(false);
    }

    @Test
    public void createWithConfigFile() throws Exception {
        SchedulerFactory factory = new StdSchedulerFactory("../resources/s");
        Scheduler scheduler= factory.getScheduler();
        scheduler.start();
        // 即使存在正在执行的作业也会被强制停止
        scheduler.shutdown(false);
    }
}
