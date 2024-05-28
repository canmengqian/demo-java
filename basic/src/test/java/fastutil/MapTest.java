package fastutil;

import cn.hutool.core.date.StopWatch;
import bean.Person;
import it.unimi.dsi.fastutil.objects.Object2ObjectAVLTreeMap;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class MapTest {
  final static   long counter =100000000;
    @Test
    void test() {
        /*Char2ObjectArrayMap<Person> map = new Char2ObjectArrayMap<Person>();
        map.put('a', new Person("a"));*/


        Map<String, Person> map2 = new HashMap<>();
        StopWatch stopWatch1 = new StopWatch();
        stopWatch1.start();
        /*for (long i = 0; i < counter; i++) {
            map2.put("" + i, new Person("2"));
        }*/
        stopWatch1.stop();
        log.info("JCF 耗时：{}", stopWatch1.getTotalTimeMillis());
        StopWatch stopWatch2 = new StopWatch();
        stopWatch2.start();
        /*Map<String, Person> map3 = HashObjObjMaps.newMutableMap();
        for (long i = 0; i < counter; i++) {
            map3.put("" + i, new Person("2"));
        }*/
        stopWatch2.stop();
        log.info("koloboke 耗时：{}", stopWatch2.getTotalTimeMillis());
        Map<String, Person> map1 = new Object2ObjectAVLTreeMap<>();
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        for (long i = 0; i < counter; i++) {
            map1.put("" + i, new Person("2"));
        }
        stopWatch.stop();
        log.info("fastutil 耗时：{}", stopWatch.getTotalTimeMillis());
        // System.out.println(map1);
    }

    void  testFastUtil(){

    }
}
