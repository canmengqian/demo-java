package design.model.template.demo1;

public class VipSickPerson extends SickProcess{
    @Override
    public boolean isVip() {
        return true;
    }

    @Override
    public void sick() {
        System.out.println ("高级专家为您候诊");
    }

    @Override
    public void pay() {
        System.out.println ("五折减免，您需要支付500元");
    }
}
