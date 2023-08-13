package guava.collections;

import com.google.common.collect.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;

@Slf4j
public class SetTest {
    @Test
    void test() {
        List<String> words = Lists.newArrayList("a", "b", "c", "b", "b", "c");
        Multiset<String> multiset1 = HashMultiset.create();
        for (String word : words) {
            multiset1.add(word);
        }
        log.info("set is {}", multiset1);
        int size = multiset1.count("b");
        log.info("b size:{}", size);

        size = multiset1.add("d", 4);
        log.info("d size:{}", size);
        // 转SET
        log.info("转SET操作");
        multiset1.elementSet().forEach(System.out::println);


    }

    @Test
    void testSort(){
        SortedMultiset<String> multiset = TreeMultiset.create ();
        multiset.setCount ("2",3);
        multiset.setCount ("3",3);
        multiset.setCount ("4",4);
        multiset.setCount ("5",5);
        //
        log.info ("正向排序");

        multiset.stream().sorted ().forEach (System.out::println);
        log.info ("逆向排序");
        multiset.descendingMultiset ().forEach (System.out::println);
    }
}
