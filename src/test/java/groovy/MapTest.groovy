package groovy

import groovy.util.logging.Slf4j
import org.junit.jupiter.api.Test

@Slf4j
class MapTest {
    @Test
    void testPut_1() {
        def map = [:];
        map.'1' = '1';
        map.'user' = 'zhangsan';
        map.'age' = 18;
        log.info("map = ${map}")
        def range = 0..5
        range.eachWithIndex { int entry, int i ->
            log.info("entry = ${entry}, i = ${i}")
            map.put(entry as String, i)
            map."${entry}" = i
        }
        log.info("map = ${map}")
        log.info("map keys = ${map.keySet()}")
        log.info("map valus : ${map.values()}")
        log.info("map size : ${map.size()}")
       /*map 带索引遍历*/
        map.eachWithIndex { Map.Entry<Object, Object> entry, int i ->
            {
                log.info("entry = ${entry}, i = ${i}")
            }
        }
    }
}
