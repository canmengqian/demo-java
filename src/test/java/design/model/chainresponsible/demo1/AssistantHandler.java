package design.model.chainresponsible.demo1;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className AssistantHandler
 * @description TODO
 * @date 2023/9/22
 */
public class AssistantHandler extends AbstractApproverHandler {
    @Override
    public Boolean approve(long days) {
        if (days <= 5) {
            System.out.println("专员审批成功");
            return true;
        }
        System.out.println("专员无法审批");
        return nextApprove(days);
    }

}
