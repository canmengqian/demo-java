package com.example.springwebdemo.liteflow.itr;

import com.yomahub.liteflow.core.NodeIteratorComponent;
import io.vavr.collection.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Iterator;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className DemoItrNode
 * @description 迭代器节点
 * @date 2024/5/22
 */
@Component("Itr")
@Slf4j
public class DemoItrNode extends NodeIteratorComponent {
    @Override
    public Iterator<?> processIterator() throws Exception {
        return List.range(0, 5).iterator();
    }
}
