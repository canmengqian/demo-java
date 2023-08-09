package guava.collections;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Lists;
import com.google.common.collect.Multiset;
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
}
