package com.example.demojava.util.event;

import org.junit.jupiter.api.Test;

import java.nio.file.WatchService;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.EventObject;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className EventTest
 * @description JDK自带的EventObject，EventListener机制
 * @date 2023/5/15
 */
public class JDKEventTest {
    @Test
    public void test() {

        ClickSource source = new ClickSource("");

        source.addListener(new AddClickListener());
        source.addListener(new DelClickListener());

        // 不调用任何监听器
        source.click();

        source.resetType("add");
        source.click();
        // 重置类型
        source.resetType("del");
        source.click();
    }
}

/**
 * 新建点击事件
 */
class ClickEvent extends EventObject {

    ClickSource source;

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ClickEvent(ClickSource source) {
        super(source);
        this.source = source;
    }
}

/**
 * 事件发布源
 */
class ClickSource {
    // 点击计数器
    private AtomicInteger clickCounter = new AtomicInteger(0);
    private List<ClickListener> listeners = new ArrayList<>();
    String type;

    public ClickSource(String type) {
        this.type = type;
    }

    public void resetType(String type) {
        this.type = type;
    }

    /**
     * 自定义事件类型，add，del用于测试不同的监听器
     */
    public void click() {
        long count = clickCounter.incrementAndGet();
        System.out.println("当前点击按钮次数:" + count);
        listeners.forEach(l -> {
            l.invoke(new ClickEvent(this));
        });
    }

    public void addListener(ClickListener listener) {
        this.listeners.add(listener);
    }


}

interface ClickListener extends EventListener {
    void invoke(ClickEvent event);
}

/**
 * 【添加】监听器
 */
class AddClickListener implements ClickListener {

    @Override
    public void invoke(ClickEvent event) {
        if ("add".equals(event.source.type)) {
            System.out.println("触发了add监听器");
        }
    }
}

/**
 * 【删除】监听器
 */
class DelClickListener implements ClickListener {

    @Override
    public void invoke(ClickEvent event) {
        if ("del".equals(event.source.type)) {
            System.out.println("触发了del监听器");
        }
    }
}




