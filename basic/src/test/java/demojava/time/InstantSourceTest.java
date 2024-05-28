package demojava.time;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.InstantSource;

@Slf4j
public class InstantSourceTest {
   @Test
    void  test(){
        InstantSource instantSource= InstantSource.system ();
        log.info ("{}",instantSource);

    }
}
