package com.example.demojava.lang;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class ComparableTest {

    public static final    int age =30;
    static  {
        System.out.println ("aa");
    }
    public   int age1 =30;



    public static void main(String[] args) {
        System.out.println (ComparableTest.age);
    }
    @Test
    void  test(){
        // 对数字排序
        List<Long> list = new ArrayList<> ();
        list.add(1L);
        list.add(2L);
        list.add(3L);
        // 倒序
        Collections.sort (list, Comparator.reverseOrder ());
        list.forEach (System.out::println);
        // 正序
        Collections.sort (list,Comparator.naturalOrder ());
        list.forEach (System.out::println);
    }

    /**
     * 对象比较
     */
    @Test
    void  testObject(){
       Person p1= Person.builder ().age (20).name ("zhangsan").build ();
       Person p2= Person.builder ().age (20).name ("lisi").build ();
       Person p3= Person.builder ().age (30).name ("wangwu").build ();

      List<Person> people=  Lists.newArrayList (p1,p2,p3);
      Comparator<Person> personComparator = (o1, o2) -> o1.age - o2.age;
      //Collections.sort (people,personComparator);
      people.sort (personComparator);
      people.forEach (System.out::println);
        System.out.println ("多轮排序,多轮比较,反转排序,按名称自然排序");
      // 反转排序,按名称自然排序
        people.sort (personComparator.reversed ().thenComparing (Person::getName));
        people.forEach (System.out::println);
        System.out.println ("使用函数式比较器进行多轮排序,多轮比较,反转排序,按名称自然排序");
      Function<Person,Integer> function= new Function<Person, Integer> () {
          @Override
          public Integer apply(Person person) {
              return 1;
          }
      };
     Integer num= function.apply (new Person ());

     Function<Integer,String> function1 = new Function<Integer, String> () {
         @Override
         public String apply(Integer integer) {
             return "";
         }
     };



        people.stream ()
                .sorted (Comparator
                        .comparing ((Function<Person, Comparable<Object>>) o -> null)
                        .reversed ()
                        .thenComparing (Person::getName)
                        .thenComparing (Person::getLike))
                .forEach (System.out::println);
    }


}
