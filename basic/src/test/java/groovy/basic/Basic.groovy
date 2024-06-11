package groovy.basic

import cn.hutool.core.text.CharSequenceUtil
import groovy.bean.User
import lombok.extern.slf4j.Slf4j
import org.hamcrest.Matchers
import org.junit.jupiter.api.Test
import spock.lang.Specification

import java.util.logging.Level
import java.util.logging.Logger

@Slf4j
class Basic extends Specification {
    private static final Logger log = Logger.getLogger(Basic.class.getName());

    void basic() {
        /*打印语句*/
        println "Hello World!"
        /*变量声明*/
        def a = 1;
        def b = "2";
        log.info("a = ${a}, b = ${b}")
    }

    @Test
    void dataType() {
        int a = 1;
        long b = 2;
        double c = 3.0;
        String d = "4";
        byte e = 5;
        short f = 6;
        boolean g = true;
        float h = 7.0f;
        log.info("基础数据类型 a = ${a}, b = ${b}, c = ${c}, d = ${d}, e = ${e}, f = ${f}, g = ${g}, h = ${h}")

        /*数据类型转换*/
        def asInt = d as int;
        log.info("数据类型转换 asInt = ${asInt}")
        def asLong = asInt as long;
        log.info("数据类型转换 asLong = ${asLong}")
        def asStr = asLong as String;
        log.info("数据类型转换 asStr = ${asStr}")
        String nullStr = null;
        Integer asInt2 = 0;
        try {
            if (CharSequenceUtil.isNotBlank(nullStr)) {
                asInt2 = nullStr as int;
            } else {
                log.warning("字符串转整形 失败")
                asInt2 = -99
            }
        } catch (NullPointerException _e) {
            log.logp(Level.WARNING, "Basic", "range", "数据类型转换 asInt2 = ${asInt2}", _e)
        }
        log.info("数据类型转换 asInt2 = ${asInt2}")
    }
    /**
     * 范围运算
     */
    void range() {
        def range = 1..10;
        log.info("范围运算 range = ${range}")
    }

    @Test
    void test() {
        Basic basic = new Basic();
        /*打印语句*/
        basic.basic();
        /*数据类型*/
        basic.dataType();
        /* 范围运算*/
        basic.range();
    }

    def "三目运算符简化测试"() {
        given:
        def str = "zhangsan"
        when:
        def result = str ?: "lisi"
        log.info("result = ${result}")
        then:
        verifyAll {
            result == "zhangsan"
        }
    }

    def "三目运算符"() {
        given:
        def user = new User()
        when:
        def name = user.name ?: ""
        name = name.toUpperCase()
        then:
        verifyAll {
            Matchers.notNullValue().matches(name)
        }
    }

    def "多层次数据访问"() {
        given:
        def arr = [
                [a: "name"],
                [b: "age"],
                [c: "sex"],
        ]
        when:
        def result = arr.a[0]
        log.info("result = ${result}")
        then:
        verifyAll {
            result == "name"
        }
    }

    def "switch模式匹配"() {
        given:
        def a = 1..3

        when:
        def rs
        switch (a) {
            case 1..3:
                rs = "range"
                break
            case "1":
                rs = "1"
                break
            case 1:
                rs = "num"
                break
        }

        then:
        verifyAll {
            rs == "range"
        }
    }


}
