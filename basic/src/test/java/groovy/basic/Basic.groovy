package groovy.basic

import cn.hutool.core.text.CharSequenceUtil
import lombok.extern.slf4j.Slf4j
import org.junit.jupiter.api.Test

import java.util.logging.Level
import java.util.logging.Logger

@Slf4j
class Basic {
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
        def asStr =asLong as String;
        log.info("数据类型转换 asStr = ${asStr}")
        String nullStr = null;
        Integer asInt2=0;
        try {
            if(CharSequenceUtil.isNotBlank(nullStr)){
                asInt2= nullStr as int;
            }else{
                log.warning("字符串转整形 失败")
                asInt2= -99
            }
        }catch (NullPointerException  _e){
            log.logp(Level.WARNING, "Basic", "range", "数据类型转换 asInt2 = ${asInt2}", _e)
        }
        log.info("数据类型转换 asInt2 = ${asInt2}")
    }
    /**
     * 范围运算
     */
    void range(){
        def  range = 1..10;
        log.info("范围运算 range = ${range}")
    }
    @Test
    void test(){
        Basic basic = new Basic();
        /*打印语句*/
        basic.basic();
        /*数据类型*/
        basic.dataType();
        /* 范围运算*/
        basic.range();
    }
}
