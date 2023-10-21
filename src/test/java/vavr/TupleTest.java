package vavr;

import domain.bean.Person;
import io.vavr.*;
import org.junit.jupiter.api.Test;

import static io.vavr.API.*;
import static io.vavr.Patterns.$Tuple2;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className TupleTest
 * @description TODO
 * @date 2023/8/28
 */
public class TupleTest {

    @Test
    void test() {
        Tuple2<Integer, Person> tuple2 = Tuple.of(1, new Person("张三"));
        System.out.println(tuple2._1);

        // 无法直接进行赋值，但是可以通过set方法进行修改值
        System.out.println(tuple2._2.toString());
        tuple2._2.setName("李四");
        System.out.println(tuple2._2.toString());
        // 拼接其他元组
        // 移除元素
        // 更新元素
        tuple2.update1 (2);
        System.out.println ("更新后元素的值为:"+tuple2._1);
        // 反转元素
        // map处理
     Tuple2<String,Person> newP=   tuple2.map1 (n->String.valueOf (n));

        // 向前或向后追加
        // 转换为Seq
        // 转换为Entry
        Tuple3<String, String, String> t3 = Tuple.of("1", "2", "3");
        Tuple8<String, String, String, String, String, String, String, String> t8 = Tuple.of("1", "2", "3", "4", "5", "6", "7", "8");
    }

    void testMatch() {

    }

    @Test
    public void testCurried() {
        Function3<Integer,Integer,Integer,Integer> sum=(a,b,c)->a+b+c;
         Function1<Integer,Function1<Integer,Function1<Integer,Integer>>> c=sum.curried ();
        Function1<Integer,Function1<Integer,Integer> > f1=c.apply (1);
        Function1<Integer,Integer> f3= f1.apply (2);
        Integer rs=f3.apply (4);
        System.out.println (rs);
      /*  final Function1<Integer,Function1<Integer,Integer>> add2= sum.curried().apply(2);
        System.out.println(add2.apply(3));   // Function3
        System.out.println(add2.apply(3).apply(4));*/
    }
}
