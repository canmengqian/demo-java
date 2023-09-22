package design.model.chainresponsible.demo1;

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
    @org.junit.jupiter.api.Test
    public void test() {
        ApproveHandler assistant = new AssistantHandler();
        ApproveHandler manager = new ManagerHandler();
        ApproveHandler boss = new BossHandler();
        assistant.setNext(manager);
        manager.setNext(boss);
        log.info("进行审批流程 1");
        // 进行审批流程 1天
        assistant.approve(1);
        log.info("进行审批流程 6天");
        // 进行审批流程 6天
        assistant.approve(6);
        log.info("进行审批流程 30天");
        // 进行审批流程 30天
        assistant.approve(30);
        log.info("进行审批流程 100天");
        // 进行审批流程
        assistant.approve(100);

    }
}
