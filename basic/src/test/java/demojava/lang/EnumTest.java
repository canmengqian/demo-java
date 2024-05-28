package demojava.lang;

import org.junit.jupiter.api.Test;

import java.util.EnumMap;
import java.util.EnumSet;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className EnumTest
 * @description TODO
 * @date 2023/8/1
 */
public class EnumTest {
    enum SWITCH {
        OFF, ON
    }

    @Test
    void testEnumMap() {
        EnumMap<SWITCH, Integer> em = new EnumMap<SWITCH, Integer>(SWITCH.class);
        em.put(SWITCH.OFF, 0);
        em.put(SWITCH.ON, 1);
        System.out.println(em.toString());
    }

    @Test
    void testEnumSet() {
        EnumSet<SWITCH> es = EnumSet.noneOf(SWITCH.class);
        es.add(SWITCH.OFF);
        es.add(SWITCH.OFF);
        es.add(SWITCH.ON);
        es.add(SWITCH.ON);
        System.out.println(es.size());
        System.out.println(es.toString());


    }
}
