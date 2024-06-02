package groovy

import groovy.util.logging.Slf4j
import io.vavr.control.Try
import org.junit.jupiter.api.Test
import spock.lang.Specification

@Slf4j
class ListTest extends Specification {
    @Test
    void testList_init() {
        /*显示声明列表类型*/
        List<String> list = new ArrayList<String>()
        /*默认创建ArrayList*/
        def list2 = [1]
        assert list2 instanceof List
        assert list2 instanceof ArrayList
        assert list2[0] == 1
/*列表类型转换*/
        list2 = list2 as LinkedList
        assert list2 instanceof LinkedList
    }
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
        /*尾部插入*/
        list.add(4)
        /*头部插入*/
        list.push(5)
        log.info("列表大小:{}", list.size())
        log.info("list = ${list}")

        /*反向获取列表元素*/
        log.info("最后一个列表元素:${list[-1]} ")
        /*取出子列表*/
        log.info("子列表:${list[1..2]}")
        /*取出子列表*/
        log.info("子列表:${list[-2, -1]}")
        log.info("子列表:${list[-1, -2]}")
    }
    /*测试列表相关的方法*/

    @Test
    void testList_2() {
        /*初始化列表*/
        def list = [0, 1, 2, 3, 4]
        /*只能作为一个元素添加*/
        //def  list2 = [0..4]
        /*不可变的列表*/
        //list=0..4
        log.info("列表的大小:${list.size()}")
        list.add(5)
        log.info("列表的大小:${list.size()}")
        list.addAll(6..10)
        log.info("列表的大小:${list.size()}")

        /*转换为不可变列表*/
        def list2 = list.asImmutable()
        Try.of { list.add(11) }
                .onSuccess { log.info("list 添加成功: ${list.size()}") }
                .onFailure { log.info("list 添加失败") }
        Try.of { list2.add(1) }.onFailure { log.info("list2 添加失败") }
        /*转换为不可变列表*/
        def list3 = list.asUnmodifiable()
        Try.of { list3.add(11) }.onFailure { log.info("list3 添加失败") }
        /*转换为同步列表*/
        list.asSynchronized()
        /*反转列表*/
        log.info("原始列表:${list},反转列表:${list.reverse()}")
        Integer max = list.grep({ it % 2 == 0 }).max()
        log.info("最大偶数:${max}")
    }

    def "列表存储任意类型"() {
        given:
        def list = [1, "2", 3.0, true, null]
        list = list as LinkedList
        expect:
        // 追加值
        list << 4
        verifyAll {
            list.size() == 5
            list[0] == 1
            list[1] == "2"
            list[2] == 3.0
            list[3] == true
            list[4] == null
            list instanceof LinkedList
        }
    }
}
