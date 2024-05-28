package guava.cache;

import com.google.common.cache.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Slf4j
public class CacheTest {
    @Test
    void test() throws ExecutionException {
        LoadingCache<String,String> cache =
                CacheBuilder.newBuilder ()
                        .expireAfterAccess (10, TimeUnit.MINUTES)
                        .expireAfterWrite (10,TimeUnit.MINUTES)
                        .initialCapacity (10)
                        .maximumSize (1000)
                        .removalListener ((RemovalListener<String, String>) n -> {
                            log.info ("移除操作,key：{}",n.getKey ());
                        })

                        .build (new CacheLoader<> () {
                            @Override
                            public String load(String key) {
                                log.info ("get value from other");
                                return key + "DB";
                            }
                        });

        cache.put ("name","zhangsan");
        cache.put ("sex","男");
        log.info ("获取key:{}",cache.getIfPresent ("name"));
        cache.invalidate ("name");
        log.info ("删除key:name");
        log.info ("获取key:{}",cache.getIfPresent ("aa"));
        log.info ("获取key:{}",cache.get ("aa"));
        log.info ("获取key:{}",cache.get ("bb",()-> "cc"));
    }
}
