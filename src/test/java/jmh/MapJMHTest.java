package jmh;

import domain.bean.Person;
import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className MapJMHTest
 * @description TODO
 * @date 2023/8/30
 */
@BenchmarkMode(Mode.All)
@Warmup(iterations = 1)
@Measurement(iterations = 1, time = 1, timeUnit = TimeUnit.SECONDS)
@Threads(1)
@Fork(0)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class MapJMHTest {
    private static final long counter = 100000;

    @Benchmark
    public void testFastUtil() {
        Map<String, Person> map1 = new Object2ObjectArrayMap<String, Person>();
        for (long i = 0; i < counter; i++) {
            map1.put("" + i, new Person("2"));
        }

    }

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
                .include(MapJMHTest.class.getSimpleName())
                .build();
        new Runner(options).run();
    }

}
