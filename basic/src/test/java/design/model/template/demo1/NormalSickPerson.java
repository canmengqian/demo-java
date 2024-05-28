package design.model.template.demo1;

public class NormalSickPerson extends SickProcess{
    @Override
    public boolean isVip() {
        return false;
    }

    @Override
    public void sick() {
        System.out.println ("普通医生候诊");
    }

    @Override
    public void pay() {
        System.out.println ("全额支付1000元");
    }
}
