package com.example.springwebdemo.liteflow;

import com.yomahub.liteflow.core.FlowExecutor;
import com.yomahub.liteflow.core.FlowExecutorHolder;
import com.yomahub.liteflow.flow.executor.DefaultNodeExecutor;
import com.yomahub.liteflow.flow.id.DefaultRequestIdGenerator;
import com.yomahub.liteflow.property.LiteflowConfig;
import com.yomahub.liteflow.thread.LiteFlowDefaultMainExecutorBuilder;
import org.junit.jupiter.api.Test;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className ManualLiteFlowTest
 * @description 手动执行
 * @date 2024/5/22
 */
public class ManualLiteFlowTest {
    /**
     * 执行最简单的示例
     */
    @Test
    void demo() {
        LiteFlowDefaultMainExecutorBuilder builder;
        DefaultRequestIdGenerator idGenerator;
        DefaultNodeExecutor nodeExecutor;
        LiteflowConfig config = new LiteflowConfig();
        config.setRuleSource("config/flow.el.xml");
        config.setRequestIdGeneratorClass(DemoRequestIdGenerator.class.getName());
        FlowExecutor executor = FlowExecutorHolder.loadInstance(config);
        executor.execute2Resp("chain1", "arg");
    }
}
