package quartz.job.demo1;

import org.junit.jupiter.api.Test;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import quartz.job.SimpleJob;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className SimpleScheduler
 * @description TODO
 * @date 2024/3/28
 */
public class SimpleScheduler {
    void test() throws SchedulerException {
        SchedulerFactory schedFact = new StdSchedulerFactory();
        Scheduler sched = schedFact.getScheduler();
        sched.start();
        // define the job and tie it to our HelloJob class
        JobDetail job = JobBuilder.newJob(SimpleJob.class)
                .withIdentity("myJob", "group1")
                .build();
        // 创建触发器
        // Trigger the job to run now, and then every 40 seconds
        Trigger trigger = newTrigger()
                .withIdentity("myTrigger", "group1")
                .startNow()
                .withSchedule(simpleSchedule()
                        .withIntervalInSeconds(5)
                        .repeatForever())
                .build();

        // Tell quartz to schedule the job using our trigger
        sched.scheduleJob(job, trigger);
    }


    /**
     * 创建标准模式的scheduler
     */
    @Test
    void createStdSchedulerByFactory() throws SchedulerException, InterruptedException {
        SchedulerFactory schedFact = new StdSchedulerFactory();
        // 创建带有名称的scheduler
        Scheduler sched =schedFact.getScheduler();
        sched.start();
        Thread.sleep(1000);
        sched.shutdown();
    }
}
