package guava.primitive;

import cn.hutool.core.collection.CollUtil;
import com.google.common.base.Joiner;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class JoinerTest {
    @Test
    void test() {
        String j1 = Joiner.on ("|").skipNulls ().join (null,"a", "b", "c");
        System.out.printf ("连接多个字符串" + j1);
        Map<String,String> maps = new LinkedHashMap<> ();
        maps.put ("a","A");
        maps.put ("b","B");
        String j2=Joiner.on (",").withKeyValueSeparator ("=").join (maps);
        log.info ("map joiner:{}",j2);

        List<Integer> list = CollUtil.newArrayList (null,1,2,3,4);
       String j3= Joiner.on ("|").useForNull ("").join (list);
       log.info ("list joiner:{}",j3);
    }
}
