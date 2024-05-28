package design.model.filter.demo1;

import cn.hutool.core.collection.CollUtil;
import org.junit.jupiter.api.Test;

import java.util.List;

public class PersonTest {
    @Test
    public  void test(){
        PersonFilterChain chain = new PersonFilterChain (Logic.OR , new WomanPersonFilter (), new AgeGe25Filter ());

        Person p1 = Person.builder().name ("张三").age (30).sex (1).build();
        Person p2 = Person.builder().name ("李四").age (30).sex (0).build();
        Person p3 = Person.builder().name ("王五").age (20).sex (0).build();

        List<Person> persons=CollUtil.newArrayList (p1,p2,p3);

      List<Person> newPersons=  chain.filter (persons);
      newPersons.forEach (p->{
          System.out.println (p.toString ());
      });
    }
}
