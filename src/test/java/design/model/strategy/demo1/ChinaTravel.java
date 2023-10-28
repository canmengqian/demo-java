package design.model.strategy.demo1;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 中国境内出行游服务
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class ChinaTravel {
    String from;

    String to ;

    double distance;

    Fee fee;


    Double getFee(){
        log.info ("您选择的出行方式为:{},您的出行路线为{}-{},出行距离:{},出行费用:{}元",fee.getType (),from,to,distance,fee.getFee (distance));
        return  fee.getFee (distance);
    }
}
