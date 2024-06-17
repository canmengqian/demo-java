package demojava.juc;

import lombok.Data;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.request.AuthFeishuRequest;
import me.zhyd.oauth.request.AuthRequest;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;

public class AtomicIntegerFieldUpdaterTest {

    @Data
    public static class Salary {
        public volatile int money;
        public volatile int moneyAtomic;
        public volatile long moneyLongAtomic;

        private AtomicIntegerFieldUpdater<Salary> counter = AtomicIntegerFieldUpdater.newUpdater(Salary.class, "moneyAtomic");
        private AtomicLongFieldUpdater<Salary> counterLong = AtomicLongFieldUpdater.newUpdater(Salary.class, "moneyLongAtomic");

        public void addAtomic() {
            counter.incrementAndGet(this);
        }

        public void addLongAtomic() {
            counterLong.incrementAndGet(this);
        }

        public void add() {
            money++;
        }

    }

    class SalaryThread extends Thread {
        Salary salary;

        public SalaryThread(Salary salary) {
            this.salary = salary;
        }

        @Override
        public void run() {
            salary.addAtomic();
            salary.addLongAtomic();
            salary.add();
        }
    }

    @Test
    void test() throws InterruptedException {

        Salary salary = new Salary();
        for (int i = 0; i < 1000; i++) {
            SalaryThread thread = new SalaryThread(salary);
            thread.start();
        }
        System.out.println("工资，未使用原子更新器" + salary.money);
        System.out.println("工资，使用原子更新器" + salary.moneyAtomic);
        System.out.println("工资，使用原子更新器Long类型" + salary.moneyLongAtomic);
        Thread.currentThread().join(2000);
    }

    /*public void renderAuth(HttpServletResponse response) throws IOException {
        AuthRequest authRequest = getAuthRequest();
        response.sendRedirect(authRequest.authorize(AuthStateUtils.createState()));
    }*/


    public Object login(AuthCallback callback) {
        AuthRequest authRequest = getAuthRequest();
        return authRequest.login(callback);
    }

    private AuthRequest getAuthRequest() {
        return new AuthFeishuRequest(AuthConfig.builder()
                .clientId("App ID")
                .clientSecret("App Secret")
                .redirectUri("重定向 URL")
                .build());
    }

}
