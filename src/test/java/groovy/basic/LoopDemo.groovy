package groovy.basic

import groovy.json.JsonSlurper
import org.junit.jupiter.api.Test

import java.util.logging.Logger

class LoopDemo {
    Logger log = Logger.getLogger(LoopDemo.class.getName());

    /* 列表循环语句*/
    void listLoop() {
        def list = [1, 2, 3, 4, 5]
        list.each {
            println it
        }
        list.eachWithIndex { item, index ->
            println "index = ${index}, item = ${item}"
        }
        list.find {
            return it > 3
        }
        list.findAll {
            return it > 3
        }
        list.findIndexOf {
            return it > 3
        }
        list.findIndexValues {
        }
    }

    /* 映射循环语句*/
    void mapLoop() {
        def map = [a: 1, b: 2, c: 3]
        map.each {
            println it
        }
        map.eachWithIndex { item, index ->
            println "index = ${index}, item = ${item}"
        }
        map.find {
            return it.value > 2
        }
        map.findAll {
            return it.value > 2
        }
        map.findIndexOf {
            return it.value > 2
        }
    }

    /* 范围循环语句*/
    void rangeLoop (){
        def a = 1..5;
        a.forEach { println it}
        a.find {
            return it > 3
        }
        for (int i = 0; i < a.size(); i++){
            println a[i]
        }
        new JsonSlurper().to
    }

    @Test
    void test(){
        LoopDemo loopDemo = new LoopDemo();
        loopDemo.listLoop();
        loopDemo.mapLoop();
        loopDemo.rangeLoop();
    }
}
