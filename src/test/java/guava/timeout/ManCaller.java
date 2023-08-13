package guava.timeout;

import java.util.concurrent.Callable;

public class ManCaller implements Callable<Integer> {

    Human human;

    public ManCaller(Human human) {
        this.human = human;
    }

    @Override
    public Integer call() throws Exception {
        human.sleep ();
        return 1;
    }
}
