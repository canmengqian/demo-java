package guava.timeout;

import com.google.common.util.concurrent.SimpleTimeLimiter;
import org.junit.jupiter.api.Test;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.*;

public class SimpleTimelimitTest {
    static ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor ();

    static {
        //核心线程数
        taskExecutor.setCorePoolSize (10);
        //线程池维护线程的最大数量,只有在缓冲队列满了之后才会申请超过核心线程数的线程
        taskExecutor.setMaxPoolSize (100);
        //缓存队列
        taskExecutor.setQueueCapacity (50);
        //许的空闲时间,当超过了核心线程出之外的线程在空闲时间到达之后会被销毁
        taskExecutor.setKeepAliveSeconds (200);
        //异步方法内部线程名称
        taskExecutor.setThreadNamePrefix ("TaskPool-01-");
        /**
         * 当线程池的任务缓存队列已满并且线程池中的线程数目达到maximumPoolSize，如果还有任务到来就会采取任务拒绝策略
         * 通常有以下四种策略：
         * ThreadPoolExecutor.AbortPolicy:丢弃任务并抛出RejectedExecutionException异常。
         * ThreadPoolExecutor.DiscardPolicy：也是丢弃任务，但是不抛出异常。
         * ThreadPoolExecutor.DiscardOldestPolicy：丢弃队列最前面的任务，然后重新尝试执行任务（重复此过程）
         * ThreadPoolExecutor.CallerRunsPolicy：重试添加当前的任务，自动重复调用 execute() 方法，直到成功
         */
        taskExecutor.setRejectedExecutionHandler (new ThreadPoolExecutor.AbortPolicy ());
        taskExecutor.setWaitForTasksToCompleteOnShutdown (true);

        taskExecutor.initialize ();
    }

    @Test
    void test() {
        ExecutorService executorService = Executors.newFixedThreadPool (10);

        SimpleTimeLimiter stl = SimpleTimeLimiter.create (executorService);
        // 测试正常方法执行
        Man man = new Man ();
        // 生成代理类
        Human proxyMan = stl.newProxy (man,Human.class,1,TimeUnit.SECONDS);
        System.out.println ("eat 方法不超时");
        proxyMan.eat ();
        System.out.println ("sleep 方法超时");
        //测试超时方法执行
        proxyMan.sleep ();
    }

    @Test
    void testCall() throws ExecutionException, TimeoutException {
        ExecutorService executorService = Executors.newFixedThreadPool (10);

        SimpleTimeLimiter stl = SimpleTimeLimiter.create (executorService);
        stl.callUninterruptiblyWithTimeout (new ManCaller (new Man ()),1,TimeUnit.SECONDS);

    }

    @Test
    void  testRunable() throws InterruptedException, TimeoutException {
        SimpleTimeLimiter stl = SimpleTimeLimiter.create (taskExecutor.getThreadPoolExecutor ());

        stl.runWithTimeout (() -> {
            try {
                Thread.sleep (3000);
            } catch (InterruptedException e) {
                throw new RuntimeException (e);
            }
        },1,TimeUnit.SECONDS);
    }


}
