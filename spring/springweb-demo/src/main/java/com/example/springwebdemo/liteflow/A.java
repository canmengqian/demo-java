package com.example.springwebdemo.liteflow;

import com.alibaba.fastjson2.JSON;
import com.yomahub.liteflow.core.NodeComponent;
import com.yomahub.liteflow.slot.DefaultContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className A
 * @description TODO
 * @date 2024/5/22
 */
@Component("a")
@Slf4j
public class A extends NodeComponent {
    @Override
    public void process() throws Exception {
        Object obj = getRequestData();
        DefaultContext context = getContextBean(DefaultContext.class);
        log.info("a context:{}", JSON.toJSONString(context));
        log.info("a");
    }
}
