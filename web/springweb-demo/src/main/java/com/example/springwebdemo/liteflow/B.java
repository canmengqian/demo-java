package com.example.springwebdemo.liteflow;

import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeComponent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className B
 * @description TODO
 * @date 2024/5/22
 */
@Component("b")
@LiteflowComponent("b")
@Slf4j
public class B extends NodeComponent {
    public void process() throws Exception {
        System.out.println("B");
    }
}
