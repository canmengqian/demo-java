package design.model.abstractFactory.demo1.factory;

import design.model.abstractFactory.demo1.pad.ApplePad;
import design.model.abstractFactory.demo1.pad.Pad;
import design.model.abstractFactory.demo1.phone.ApplePhone;
import design.model.abstractFactory.demo1.phone.Phone;

public class AppleElectFactory implements ElectFactory{
    @Override
    public Phone createPhone() {
        return new ApplePhone ();
    }

    @Override
    public Pad creadPad() {
        return new ApplePad ();
    }
}
