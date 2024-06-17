package bean;


import org.junit.jupiter.api.Test;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className Man
 * @description TODO
 * @date 2024/6/12
 */
public class Man extends JaPerson {
    public void manWalk() {
        System.out.println("man walk");
    }

    @Test
    public void test_walk() {
        JaPerson person = new Man();
        if (person instanceof Man) {
            ((Man) person).manWalk();
        }

        Man man = (Man) new JaPerson();
        System.out.println(man.getClass().getName());
        man.manWalk();
    }
}
