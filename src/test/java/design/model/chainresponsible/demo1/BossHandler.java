package design.model.chainresponsible.demo1;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className BossHandler
 * @description TODO
 * @date 2023/9/22
 */
public class BossHandler extends AbstractApproverHandler {
    @Override
    public Boolean approve(long days) {
        if (days > 10 && days < 100) {
            System.out.println("boss审批成功");
            return true;
        }
        System.out.println("boss无法审批");
        return nextApprove(days);
    }
}
