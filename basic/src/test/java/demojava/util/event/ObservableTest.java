package demojava.util.event;

import org.junit.jupiter.api.Test;

import java.util.Observable;
import java.util.Observer;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className ObservableTest
 * @description 基于观察者模式实现 按钮监听
 * @date 2023/5/15
 */
public class ObservableTest {
    @Test
    public void test() {
        // 创建被观察者
        ClickObservable observable = new ClickObservable();
        // 创建观察者
        AddObserver addObserver = new AddObserver();
        // 向被观察者添加 1...N个观察者
        observable.addObserver(addObserver);

        // 生成click事件并通知给观察者
        observable.click("add");
        observable.click("add");
        observable.click("add");

    }
}

/**
 * 1. 被观察者默认实现了向 观察者通知功能，能够动态增加和删除观察者
 */
class ClickObservable extends Observable {
    public void click(String type) {
        this.setChanged();
        this.notifyObservers(type);
    }
}

class AddObserver implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        if ("add".equals(arg.toString())) {
            System.out.println("新增事件处理");
        }
    }
}