package com.example.springwebdemo.liteflow;

import com.yomahub.liteflow.core.NodeComponent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className C
 * @description TODO
 * @date 2024/5/22
 */
@Component("c")
@Slf4j
public class C extends NodeComponent {
    @Override
    public void process() throws Exception {
        log.info("C执行");
    }
}
