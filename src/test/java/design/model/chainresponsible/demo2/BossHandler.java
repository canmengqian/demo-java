package design.model.chainresponsible.demo2;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className BossHandler
 * @description TODO
 * @date 2023/9/22
 */
public class BossHandler implements ApproveHandler {
    @Override
    public Boolean approve(long days) {
        if (days < 100) {
            System.out.println("boss审批成功");
            return true;
        }
        System.out.println("boss无法审批");
        return false;
    }
}
