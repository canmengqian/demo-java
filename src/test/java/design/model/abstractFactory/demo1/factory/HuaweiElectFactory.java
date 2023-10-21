package design.model.abstractFactory.demo1.factory;

import design.model.abstractFactory.demo1.pad.HuaweiPad;
import design.model.abstractFactory.demo1.pad.Pad;
import design.model.abstractFactory.demo1.phone.HuaweiPhone;
import design.model.abstractFactory.demo1.phone.Phone;

public class HuaweiElectFactory implements ElectFactory{
    @Override
    public Phone createPhone() {
        return new HuaweiPhone ();
    }

    @Override
    public Pad creadPad() {
        return new HuaweiPad ();
    }
}
