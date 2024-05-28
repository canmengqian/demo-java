package com.example.springwebdemo.liteflow;

import com.alibaba.fastjson2.JSON;
import com.yomahub.liteflow.core.FlowExecutor;
import com.yomahub.liteflow.flow.LiteflowResponse;
import com.yomahub.liteflow.slot.DefaultContext;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className DemoLiteFlowTest
 * @description TODO
 * @date 2024/5/22
 */
@SpringBootTest
@Slf4j
public class DemoLiteFlowTest {
    @Resource
    FlowExecutor flowExecutor;

    @Test
    void testFlow() {
        DefaultContext dc = new DefaultContext();
        dc.setData("name", "zhangsan");
        dc.setData("rs", "init");
        LiteflowResponse response = flowExecutor.execute2Resp("chain1", "arg", dc);
        DefaultContext context = response.getContextBean(DefaultContext.class);
        log.info("执行结果:{}", JSON.toJSONString(context));
    }

    @Test
    @Tag("执行End节点")
    void testEnd() {
        DefaultContext dc = new DefaultContext();
        dc.setData("name", "zhangsan");
        dc.setData("rs", "init");
        LiteflowResponse response = flowExecutor.execute2Resp("chain2", "arg", dc);
        DefaultContext context = response.getContextBean(DefaultContext.class);
        log.info("执行结果:{}", JSON.toJSONString(context));
    }

    @Test
    @Tag("提前执行End节点,后续节点不执行")
    void testPreEnd() {
        DefaultContext dc = new DefaultContext();
        dc.setData("name", "zhangsan");
        dc.setData("rs", "init");
        LiteflowResponse response = flowExecutor.execute2Resp("chain3", "arg", dc);
        DefaultContext context = response.getContextBean(DefaultContext.class);
        log.info("执行结果:{}", JSON.toJSONString(context));
    }

    @Test
    @Tag("执行单个的迭代器节点")
    void testIteratorNode() {
        LiteflowResponse response = flowExecutor.execute2Resp("itr_chain_1", "arg");
        DefaultContext context = response.getContextBean(DefaultContext.class);
        log.info("执行结果:{}", JSON.toJSONString(context));
    }
    @Test
    @Tag("执行单个的For循环节点")
    void testForNode() {
        LiteflowResponse response = flowExecutor.execute2Resp("for_chain_1", "arg");
        DefaultContext context = response.getContextBean(DefaultContext.class);
        log.info("执行结果:{}", JSON.toJSONString(context));
    }
}
