package groovy.trait

import org.junit.jupiter.api.Test

class TraitTest {
    /*测试trait*/

    @Test
    void testTrait_1() {
        Bird bird = new Bird();
        // 使用特征方法
        bird.fly()
        // 覆写 fly
        def duck = new Duck();
        duck.fly()
    }
}
