package design.model.bridge.demo1.additive;

import design.model.bridge.demo1.additive.Additive;

public class MilkAdditive implements Additive {
    @Override
    public String getType() {
        return "牛奶";
    }
}
