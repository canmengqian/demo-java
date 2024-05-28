package design.model.facede.demo1;

import lombok.extern.slf4j.Slf4j;

/**
 * 收银员
 */
@Slf4j
public class Cashier {
    public void settle(double cash){
        log.info ("收银算账,一共收取:{}元",cash);
    }
}
