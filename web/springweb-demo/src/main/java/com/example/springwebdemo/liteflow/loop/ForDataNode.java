package com.example.springwebdemo.liteflow.loop;

import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeComponent;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className ForDataNode
 * @description for数据节点,接收for循环的循环次数
 * @date 2024/5/22
 */
@LiteflowComponent("fData")
@Slf4j
public class ForDataNode extends NodeComponent {
    @Override
    public void process() throws Exception {
        Integer index=getLoopIndex();
        log.info("forData:当前循环次数{}",index);
    }
}
