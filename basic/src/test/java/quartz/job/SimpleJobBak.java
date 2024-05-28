package quartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className SimpleJobBak
 * @description TODO
 * @date 2024/2/6
 */
public class SimpleJobBak implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("替换性质的任务");
    }
}
