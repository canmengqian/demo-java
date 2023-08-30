package koloboke;

import com.koloboke.collect.map.hash.HashCharObjMap;
import com.koloboke.collect.map.hash.HashCharObjMaps;
import com.koloboke.collect.map.hash.HashObjObjMaps;
import domain.bean.Person;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className MapTest
 * @description TODO
 * @date 2023/8/30
 */
public class MapTest {
    @Test
    void test() {
        HashCharObjMap<Person> map = HashCharObjMaps.newMutableMap();
        map.put('a', new Person());
        System.out.println(map.toString());

        Map<String, Person> map2 = HashObjObjMaps.newMutableMap();
        map2.put("1", new Person("1"));
        System.out.println(map2);
    }
}
