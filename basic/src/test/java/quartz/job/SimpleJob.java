package quartz.job;

import cn.hutool.core.date.DateUtil;
import org.junit.jupiter.api.Test;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className SimpleJob
 * @description TODO
 * @date 2024/2/6
 */
public class SimpleJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println(DateUtil.now() + "执行任务" + jobExecutionContext.getMergedJobDataMap().get("name"));
    }

    // 1.创建调度器 Scheduler
    private static SchedulerFactory factory = new StdSchedulerFactory();

    JobKey jobDetailKey = JobKey.jobKey("job1", "group1");
    // 2.创建JobDetail实例，并与MyJob类绑定(Job执行内容)
    JobDetail jobDetail = JobBuilder.newJob(SimpleJob.class)
            .withIdentity(jobDetailKey)
            .build();

    JobDetail jobDetailBak = JobBuilder.newJob(SimpleJobBak.class)
            .withIdentity(jobDetailKey)
            .build();


    TriggerKey fiveSecTriggerKey = TriggerKey.triggerKey("trigger1");
    private Trigger fiveSecTrigger = TriggerBuilder.newTrigger().forJob(jobDetail)
            .withDescription("简单定时任务")
            .withIdentity(fiveSecTriggerKey)
            .usingJobData("name", "simple - job,五秒执行")
            .withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(5))
            .withPriority(5)
            .build();
    TriggerKey oneSecTriggerKey = TriggerKey.triggerKey("trigger1-1");
    private Trigger oneSecTrigger = TriggerBuilder.newTrigger().forJob(jobDetail)
            .withDescription("简单定时任务-1秒")
            .withIdentity(oneSecTriggerKey)
            .usingJobData("name", "simple - job 每秒执行")
            .withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(1))
            .withPriority(1)
            .startNow()
            .build();


    @Test
    void testPauseAndReStart() throws SchedulerException, InterruptedException {
        Scheduler scheduler = factory.getScheduler();

       /* // 暂停 所有任务,指定任务,按JOB组指定,按Trigger key指定, 按Trigger组指定
        scheduler.pauseAll();
        scheduler.pauseJobs(GroupMatcher.groupEndsWith(""));
        // 通过替换trigger重新规划任务
        scheduler.rescheduleJob(null, null);
        // 恢复任务: 恢复任务所有任务,恢复指定任务,按组恢复任务
        // 恢复任务所有任务
        scheduler.resumeAll();
        // 恢复指定任务
        scheduler.resumeJob(jobDetail.getKey());
        scheduler.resumeJobs(GroupMatcher.groupEndsWith(""));
        // 按trigger恢复任务,按trigger组匹配器匹配任务
        scheduler.resumeTrigger(TriggerKey.triggerKey(""));
        scheduler.resumeTriggers(GroupMatcher.groupEndsWith(""));*/
        // 4.执行，开启调度器
        scheduler.scheduleJob(jobDetail, oneSecTrigger);
        System.out.println(System.currentTimeMillis());
        scheduler.start();
        //主线程睡眠1分钟，然后关闭调度器
        Thread.currentThread().join(60000);
        System.out.println(System.currentTimeMillis());
    }

    /**
     * 新增或替换原始任务
     *
     * @throws SchedulerException
     * @throws InterruptedException
     */
    @Test
    void testAddOrReplaceJob() throws SchedulerException, InterruptedException {
        Scheduler scheduler = factory.getScheduler();
        scheduler.scheduleJob(jobDetail, oneSecTrigger);
        System.out.println(System.currentTimeMillis());
        scheduler.start();
        Thread.currentThread().join(5000);
        // 如果任务已存在,则替换原始任务,否则按新任务执行
        // scheduler.addJob(jobDetailBak, true, true);
        // 参数2为false会抛出 job已存在的异常
        scheduler.addJob(jobDetailBak, false, true);
        Thread.currentThread().join(50000);
        System.out.println(System.currentTimeMillis());
    }

    /**
     * 测试暂停和恢复任务
     *
     * @throws SchedulerException
     * @throws InterruptedException
     */
    @Test
    void testPauseAndResumeJob() throws SchedulerException, InterruptedException {
        Scheduler scheduler = factory.getScheduler();
        scheduler.scheduleJob(jobDetail, oneSecTrigger);
        scheduler.start();
        Thread.currentThread().join(5000);
        // 暂停任务
        scheduler.pauseJob(jobDetailKey);
        System.out.println("任务暂停了");
        Thread.currentThread().join(5000);
        // 恢复任务
        scheduler.resumeJob(jobDetailKey);
        Thread.currentThread().join(5000);
    }

    /**
     * 变更调度器
     */
    @Test
    void changeTrigger() throws SchedulerException, InterruptedException {
        Scheduler scheduler = factory.getScheduler();
        scheduler.scheduleJob(jobDetail, oneSecTrigger);
        scheduler.start();
        Thread.currentThread().join(3000);
        scheduler.scheduleJob(jobDetail, fiveSecTrigger);
        //TODO 不知道作用
        scheduler.rescheduleJob(oneSecTriggerKey, fiveSecTrigger);
        Thread.currentThread().join(5000);
    }

    /**
     * 相同job不同trigger 不能重复调用scheduleJob ，会抛出异常
     *
     * @throws SchedulerException
     * @throws InterruptedException
     */
    @Test
    void multSameJobTrigger() throws SchedulerException, InterruptedException {
        Scheduler scheduler = factory.getScheduler();
        scheduler.scheduleJob(jobDetail, oneSecTrigger);
        scheduler.start();
        Thread.currentThread().join(3000);
        scheduler.scheduleJob(jobDetail, fiveSecTrigger);
        Thread.currentThread().join(30000);
    }

    /**
     * 更换触发器需要先删除原始job,然后在重新scheduleJob
     *
     * @throws SchedulerException
     * @throws InterruptedException
     */
    @Test
    void multSameJobTrigger_1() throws SchedulerException, InterruptedException {
        Scheduler scheduler = factory.getScheduler();
        scheduler.scheduleJob(jobDetail, oneSecTrigger);
        scheduler.startDelayed(1);
        Thread.currentThread().join(3000);
        scheduler.deleteJob(jobDetailKey);
        scheduler.scheduleJob(jobDetail, fiveSecTrigger);
        Thread.currentThread().join(30000);
    }

    /**
     * 手动触发任务
     *
     * @throws SchedulerException
     * @throws InterruptedException
     */
    @Test
    void manuaTrigger() throws SchedulerException, InterruptedException {
        Scheduler scheduler = factory.getScheduler();
        scheduler.scheduleJob(jobDetail, oneSecTrigger);
        scheduler.start();
        Thread.currentThread().join(3000);
        JobDataMap dataMap= new JobDataMap();
        dataMap.put("name","立即执行的任务参数");
        scheduler.triggerJob(jobDetailKey,dataMap);
        Thread.currentThread().join(3000);
    }

    public static void main(String[] args) throws SchedulerException, InterruptedException {

    }
}
