package quartz.job.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.DefaultThreadExecutor;
import org.quartz.impl.DirectSchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.simpl.SimpleInstanceIdGenerator;
import org.quartz.simpl.SimpleThreadPool;
import org.quartz.spi.InstanceIdGenerator;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className DirectSchedulerFactoryTest
 * @description TODO
 * @date 2024/4/9
 */
@Slf4j
public class DirectSchedulerFactoryTest {
    @Test
    void createScheduler() throws SchedulerException {
        DirectSchedulerFactory factory = DirectSchedulerFactory.getInstance();
        InstanceIdGenerator idGenerator = new SimpleInstanceIdGenerator();
        factory.createScheduler("Test-Scheduler1",idGenerator.generateInstanceId(),
                new SimpleThreadPool(10,Thread.NORM_PRIORITY),
                new org.quartz.simpl.RAMJobStore());
        factory.createScheduler("Test-Scheduler2",idGenerator.generateInstanceId(),
                new SimpleThreadPool(10,Thread.NORM_PRIORITY),
                new org.quartz.simpl.RAMJobStore());
        // 与创建方法互斥
        //factory.createVolatileScheduler(10);
        for (Scheduler s : factory.getAllSchedulers()) {
            log.info("scheduler:{},id:{}", s.getSchedulerName(),s.getSchedulerInstanceId());
            s.start();
        }

    }
}
