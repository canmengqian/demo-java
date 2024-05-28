package design.model.abstractFactory.demo1.factory;

import design.model.abstractFactory.demo1.pad.Pad;
import design.model.abstractFactory.demo1.phone.Phone;

public interface ElectFactory {
    Phone createPhone();

    Pad creadPad();
}
