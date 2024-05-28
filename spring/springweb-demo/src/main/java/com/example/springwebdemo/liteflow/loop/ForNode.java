package com.example.springwebdemo.liteflow.loop;

import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeForComponent;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className ForNode
 * @description TODO
 * @date 2024/5/22
 */
@LiteflowComponent("f")
@Slf4j
public class ForNode extends NodeForComponent {
    @Override
    public int processFor() throws Exception {
        return 3;
    }
}
