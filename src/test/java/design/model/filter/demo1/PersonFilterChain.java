package design.model.filter.demo1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class PersonFilterChain  implements  Filter<List<Person>,List<Person>>{
    List<PersonFilter> personFilterList = new ArrayList<> ();

    Logic logic;

    public PersonFilterChain( Logic logic) {
        if(logic==null){
            logic = Logic.AND;
        }
        this.logic = logic;
    }

    public PersonFilterChain(Logic logic,PersonFilter... filters) {
        if(filters!=null){
            for(PersonFilter filter:filters){
                if(filter!=null){
                    personFilterList.add (filter);
                }
            }
        }
        if(logic==null){
            logic = Logic.AND;
        }
        this.logic = logic;
    }


    @Override
    public List<Person> filter(List<Person> people) {
        // 声明新的结果集
        List<Person> newRs = new ArrayList<> ();
        // 声明推断
        List<Predicate<Person>> ps = new ArrayList<> ();
        for (PersonFilter personFilter: personFilterList){
            Predicate<Person> p = (P)-> personFilter.filter (P);
            ps.add (p);
        }

        List<Boolean> predictRs = new ArrayList<> ();
         if(Logic.OR.equals (logic)){
             for (Person p:people){
                long trueNums= ps.stream().map (P->P.test (p)).filter (B->Boolean.TRUE.equals (B)).count ();
                if(trueNums>0){
                    newRs.add (p);
                }
             }
         }
         else if(Logic.AND.equals (logic)){
             for (Person p:people){
                 long falseNums= ps.stream().map (P->P.test (p)).filter (B->Boolean.FALSE.equals (B)).count ();
                 if(falseNums==0){
                     newRs.add (p);
                 }
             }
         }
         return newRs;
    }
}
