package design.model.adaptor.demo1;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class HdmiTest {
    @Test
    public void testVga2Hdmi(){
        HdmiAdaptee hdmiAdaptee = new Vga2HdmiAdaptor ();
     Hdmi hdmi=   hdmiAdaptee.getHdmi ();
     log.info (hdmi.toString ());
    }
}
