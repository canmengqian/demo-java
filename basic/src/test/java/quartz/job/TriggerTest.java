package quartz.job;

import org.junit.jupiter.api.Test;
import org.quartz.*;
import org.quartz.impl.triggers.CronTriggerImpl;

import java.text.ParseException;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className TriggerTest
 * @description TODO
 * @date 2024/2/6
 */
public class TriggerTest {
    public static JobKey jobDetailKey = JobKey.jobKey("jobbak", "group1");
    public static JobDetail jobDetailBak = JobBuilder.newJob(SimpleJobBak.class)
            .withIdentity(jobDetailKey)
            .build();

    CronTriggerImpl trigger3 = new CronTriggerImpl("cron", "group3", "0/5 * * * * ? *");

    public TriggerTest() throws ParseException {
    }

    @Test
    void testCronTrigger() throws SchedulerException, ParseException, InterruptedException {
        CronTriggerImpl trigger = new CronTriggerImpl("cron", "group1");
        trigger.setCronExpression("* * * * * ?");

        Scheduler scheduler = AppConst.factory.getScheduler();
        scheduler.scheduleJob(AppConst.jobDetail, trigger);
        scheduler.startDelayed(5);
        CronTriggerImpl trigger2 = new CronTriggerImpl("cron", "group2");
        trigger2.setCronExpression("* * * * * ?");
        scheduler.scheduleJob(jobDetailBak, trigger2);
        scheduler.deleteJob(AppConst.jobDetailKey);

        JobDataMap map = new JobDataMap();
        map.put("name", "我是cron 3");
        trigger3.setJobDataMap(map);
        scheduler.scheduleJob(AppConst.jobDetail, trigger3);
        scheduler.pauseJob(AppConst.jobDetailKey);
        scheduler.resumeJob(AppConst.jobDetailKey);
        Thread.currentThread().join(30000);
    }
    @Test
    void testDelayTrigger() throws SchedulerException, ParseException, InterruptedException {
        CronTriggerImpl trigger = new CronTriggerImpl("cron", "group1");
        trigger.setCronExpression("* * * * * ?");
      // 设置失火指令级别
         trigger.setMisfireInstruction(-1);
        Scheduler scheduler = AppConst.factory.getScheduler();

        scheduler.scheduleJob(AppConst.jobDetail, trigger);
        scheduler.start();
        Thread.currentThread().join(3000);
    }
}
