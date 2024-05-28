package disruptor;

import cn.hutool.core.thread.ThreadFactoryBuilder;
import cn.hutool.core.util.RandomUtil;
import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.EventTranslator;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import sun.misc.Unsafe;

@Slf4j
public class SimpleDisRuptorTest {
    @Test
    void test() throws InterruptedException {
       int n= 144 + ((0 & 3) << 2);
        System.out.println (n);
        Disruptor<LongEvent> disruptor = new Disruptor<> (new LongEventFactory (), 4, new ThreadFactoryBuilder ().build (), ProducerType.SINGLE, new BlockingWaitStrategy ());
        LongEvent firstEle = disruptor.get (0);
        log.info ("首个元素的Hash值为:{}", firstEle.hashCode ());
        disruptor.getBufferSize ();
        long cursor = disruptor.getCursor ();
       disruptor.handleEventsWith (new LongHandler ());
        disruptor.start ();
        log.info ("游标值为:{}", cursor);
        //disruptor.start ();
        for (int i = 0; i < 100; i++) {
            int finalI = i;
            disruptor.getRingBuffer ().publishEvent ((event, sequence) -> {
                event.setValue (finalI);
                log.info ("开始发布事件,当前的序列是:{},事件的hash值是:{}", sequence, event.hashCode ());
            });
            log.info ("循环第{}次", i);
        }
        Thread.currentThread ().join ();


    }

    @Data
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class LongEvent {
        long value;
    }

    public static class LongEventFactory implements EventFactory<LongEvent> {

        @Override
        public LongEvent newInstance() {
            LongEvent event = new LongEvent (RandomUtil.randomLong (10));
            log.info ("初始化时的hash:{}", event.hashCode ());
            return event;
        }
    }

    public static class LongHandler implements EventHandler<LongEvent> {

        @Override
        public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {
            //Thread.sleep (1000);
            log.info ("事件处理器正在处理序列:{}", sequence);
            // Thread.sleep (1000);
        }
    }


}
