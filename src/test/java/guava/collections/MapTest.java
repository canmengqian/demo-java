package guava.collections;

import com.google.common.collect.MapMaker;
import com.google.common.collect.Maps;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className MapTest
 * @description TODO
 * @date 2023/8/8
 */
public class MapTest {
    @Test
    void test() {
        Maps.newHashMap();
        Maps.newLinkedHashMap();
    }

    @Test
    void testMapMaker() {
        ConcurrentMap<String, String> concurrentHashMap = new MapMaker().makeMap();
        concurrentHashMap.put("a", "b");
        System.out.println(concurrentHashMap.toString());
    }
}
