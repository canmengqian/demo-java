package design.model.iterator.demo1;

import org.junit.jupiter.api.Test;

public class SimpleArrayTest {
    @Test
    public void test(){
        SimpleArray<Integer> array = new SimpleArray<> ();
        array.add (1);
        array.add (2);
        array.add (3);
       Iterator<Integer> iterator= array.iterator ();
        while (iterator.hasNext ()){
            System.out.println (iterator.next ());
        }
        System.out.println (iterator.hasNext ());
    }
}
