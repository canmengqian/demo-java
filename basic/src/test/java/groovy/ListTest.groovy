package groovy

import groovy.util.logging.Slf4j
import org.junit.jupiter.api.Test

@Slf4j
class ListTest {
    /*测试列表添加*/

    @Test
    void testList_1() {
        /*初始化列表*/
        def list = [0] as LinkedList<Integer>
        list.add(1)
        list[2] = 2
        /*追加操作*/
        list << 3
        log.info("list = ${list}")
    }
}
