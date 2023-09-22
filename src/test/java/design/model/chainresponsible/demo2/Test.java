package design.model.chainresponsible.demo2;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className Test
 * @description TODO
 * @date 2023/9/22
 */
@Slf4j
public class Test {
    /**
     * 链式生成职责链
     */
    @org.junit.jupiter.api.Test
    public void test() {
        log.info("处理1天假期");
        ApproveManager.instance().add(new AssistantHandler())
                .add(new ManagerHandler())
                .add(new BossHandler())
                .approve(1);
        log.info("处理5天假期");
        ApproveManager.instance()
                .add(new AssistantHandler())
                .add(new ManagerHandler())
                .add(new BossHandler())
                .approve(5);
        log.info("处理10天假期");
        ApproveManager.instance().add(new AssistantHandler()).add(new ManagerHandler()).add(new BossHandler()).approve(10);
        log.info("处理100天假期");
        ApproveManager.instance().add(new AssistantHandler()).add(new ManagerHandler()).add(new BossHandler()).approve(100);

    }
    /**
     * 静态方式职责链
     */
    @org.junit.jupiter.api.Test
    public void testStatic() {
        ApproveStaticManager.add(new AssistantHandler());
        ApproveStaticManager.add(new ManagerHandler());
        ApproveStaticManager.add(new BossHandler());
        log.info("处理1天假期");
        ApproveStaticManager.approve(1);
        log.info("处理5天假期");
        ApproveStaticManager.approve(5);
        log.info("处理10天假期");
        ApproveStaticManager.approve(10);
        log.info("处理100天假期");
        ApproveStaticManager.approve(100);
    }
}
