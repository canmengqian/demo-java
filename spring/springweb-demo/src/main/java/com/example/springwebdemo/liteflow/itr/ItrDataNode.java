package com.example.springwebdemo.liteflow.itr;

import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeComponent;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className ItrDataNode
 * @description 用于接收迭代器中每个元素值的数据节点
 * @date 2024/5/22
 */
@LiteflowComponent("itrData")
@Slf4j
public class ItrDataNode extends NodeComponent {
    @Override
    public void process() throws Exception {
        Integer num = getCurrLoopObj();
        log.info("迭代器数据节点接收到的值为:{}",num);
    }
}
