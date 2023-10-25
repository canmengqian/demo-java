package design.model.adaptor.demo1;

import lombok.extern.slf4j.Slf4j;

/**
 * VGA模式转Hdmi模式
 */
@Slf4j
public class Vga2HdmiAdaptor extends VgaAdaptee implements HdmiAdaptee{
    @Override
    public Hdmi getHdmi() {
        log.info ("获取VGA信息");
        log.info ("VGA信息:{}",getVga ());
        log.info ("接口转换中");
        Hdmi hdmi =  new Hdmi ("高清视频","高清音频");
        return hdmi;
    }
}
