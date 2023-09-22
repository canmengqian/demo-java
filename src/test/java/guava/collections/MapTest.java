package guava.collections;

import com.google.common.collect.*;
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

    @Test
    void testMultiMap(){
        ListMultimap<String,Integer> listMultimap = MultimapBuilder.treeKeys ().arrayListValues ().build ();
        listMultimap.put ("1",1);
        listMultimap.put ("1",1);
        listMultimap.put ("1",2);
        listMultimap.put ("1",3);
        System.out.println (listMultimap.get ("1"));
        listMultimap.remove ("1",1);
        System.out.println (listMultimap.get ("1"));

    }
}
