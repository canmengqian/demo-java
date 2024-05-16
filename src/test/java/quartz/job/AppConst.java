package quartz.job;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className AppConst
 * @description TODO
 * @date 2024/2/6
 */
public class AppConst {
    // 1.创建调度器 Scheduler
    public static SchedulerFactory factory = new StdSchedulerFactory();

    public static JobKey jobDetailKey = JobKey.jobKey("job1", "group1");
    // 2.创建JobDetail实例，并与MyJob类绑定(Job执行内容)
    public static JobDetail jobDetail = JobBuilder.newJob(SimpleJob.class)
            .withIdentity(jobDetailKey)
            .build();

    public static JobDetail jobDetailBak = JobBuilder.newJob(SimpleJobBak.class)
            .withIdentity(jobDetailKey)
            .build();


    public static TriggerKey fiveSecTriggerKey = TriggerKey.triggerKey("trigger1");
    public static Trigger fiveSecTrigger = TriggerBuilder.newTrigger().forJob(jobDetail)
            .withDescription("简单定时任务")
            .withIdentity(fiveSecTriggerKey)
            .usingJobData("name", "simple - job,五秒执行")
            .withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(5))
            .withPriority(5)
            .build();
    public static TriggerKey oneSecTriggerKey = TriggerKey.triggerKey("trigger1-1");
    public static Trigger oneSecTrigger = TriggerBuilder.newTrigger().forJob(jobDetail)
            .withDescription("简单定时任务-1秒")
            .withIdentity(oneSecTriggerKey)
            .usingJobData("name", "simple - job 每秒执行")
            .withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(1))
            .withPriority(1)
            .startNow()
            .build();
}
