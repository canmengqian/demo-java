package lombok;

import cn.hutool.core.bean.BeanUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className LombokTest
 * @description TODO
 * @date 2023/9/22
 */
@Slf4j
public class LombokTest {
    /**
     * 测试@value注解
     */
    record DogRecord(String name, String like, Weight weight) {

    }

    ;

    @Test
    void testValue() {
        Dog dog1 = new Dog("dog1", "meat", new Weight(2.0));
        DogCopy dog2 = new DogCopy();
        BeanUtil.copyProperties(dog1, dog2);
        dog2.name = "dog2";
        log.info(dog1.toString());
        log.info(dog2.toString());
        dog2.weight.setWeight(3.0);

        log.info(dog1.toString());
        log.info(dog2.toString());

        /**
         * dog2和record间进行复制，测试可变性
         */
        DogCopy dog3 = new DogCopy();
        DogRecord dog4 = new DogRecord("dog4", "water", new Weight(4.0));
        /*
        record类未生成get方法,生成了属性方法可以获取属性值,但是会导致bean拷贝失效
         */
        BeanUtil.copyProperties(dog4, dog3);
        log.info(dog3.toString());
        log.info(dog4.toString());
      /*  dog3.weight.setWeight(5.0);
        log.info(dog3.toString());
        log.info(dog4.toString());*/
    }

    @Value
    class Dog {
        String name;
        String like;
        Weight weight;
    }

    @Data
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    class DogCopy {
        String name;
        String like;
        Weight weight;
    }

    @Data
    @AllArgsConstructor
    class Weight {
        double weight;
    }
}
