package com.example.springwebdemo.liteflow;

import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeComponent;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className End
 * @description End节点, 标识所有流程结束，不再向后执行
 * @date 2024/5/22
 */
@LiteflowComponent("end")
@Slf4j
public class End extends NodeComponent {
    @Override
    public void process() throws Exception {
        log.info("流程结束");
    }

    @Override
    public boolean isAccess() {
        return true;
    }

    @Override
    public boolean isContinueOnError() {
        return false;
    }

    @Override
    public boolean isEnd() {
        log.info("流程结束");
        return true;
    }

    @Override
    public void beforeProcess() {
        super.beforeProcess();
        log.info("开始进入结束流程节点");
    }

    @Override
    public void onSuccess() throws Exception {
        log.info("结束流程节点执行成功");
    }

    @Override
    public void onError(Exception e) throws Exception {
      log.error("结束流程节点执行失败,{}",e);
    }

    @Override
    public void afterProcess() {
        log.info("End节点执行完成");
    }
}
