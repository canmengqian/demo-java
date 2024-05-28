package resilience4j.bulkhead;

import io.github.resilience4j.bulkhead.Bulkhead;
import io.github.resilience4j.bulkhead.BulkheadConfig;
import io.github.resilience4j.bulkhead.BulkheadRegistry;
import org.junit.jupiter.api.Test;


import java.time.Duration;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className BulkheadTest
 * @description TODO
 * @date 2023/8/24
 */
public class BulkheadTest {
    @Test
    void test() {
        BulkheadConfig config = BulkheadConfig.custom()
                .maxConcurrentCalls(20)
                .maxWaitDuration(Duration.ofMillis(10))
                .build();
        BulkheadRegistry registry = BulkheadRegistry.of(config);
        Bulkhead bulkhead = registry.bulkhead("test");
        bulkhead.acquirePermission();
        bulkhead.acquirePermission();
        System.out.println("hello");
        for (int i = 0; i < 10; i++) {
            bulkhead.executeRunnable(() -> {
                System.out.println(Thread.currentThread().getName());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }

    }
}
