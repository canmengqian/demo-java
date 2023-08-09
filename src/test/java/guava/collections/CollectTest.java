package guava.collections;

import com.google.common.collect.*;
import com.google.common.primitives.ImmutableDoubleArray;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className CoolectTest
 * @description TODO
 * @date 2023/8/8
 */
public class CollectTest {
    @Test
    void testLists() {
        List<Integer> arrList = Lists.newArrayList(1, 2, 3);
        Lists.newLinkedList();
        Lists.newCopyOnWriteArrayList();
        // 列表切分
        List<List<Integer>> partitions = Lists.partition(Lists.newArrayList(1, 2, 3), 1);

        // 列表反转
        arrList = Lists.reverse(arrList);
        arrList.forEach(System.out::println);
    }

    @Test
    void testSets() {
        Sets.newHashSet();
        Sets.newLinkedHashSet();

    }

    @Test
    void testImutable() {
        ImmutableBiMap<String, String> biMap = ImmutableBiMap.<String, String>builder().put("a", "b").build();
        ImmutableDoubleArray itd = ImmutableDoubleArray.builder().build();
        ImmutableList<String> itl = ImmutableList.<String>builder().build();
        ImmutableMultiset<String> its = ImmutableMultiset.<String>builder().build();
        ImmutableSortedSet<String> itss = ImmutableSortedSet.copyOf(new String[]{"1", "2", "4"});
    }
}
