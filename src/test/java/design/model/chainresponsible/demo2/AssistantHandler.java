package design.model.chainresponsible.demo2;

import design.model.chainresponsible.demo1.AbstractApproverHandler;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className AssistantHandler
 * @description TODO
 * @date 2023/9/22
 */
public class AssistantHandler implements ApproveHandler {
    @Override
    public Boolean approve(long days) {
        if (days < 5) {
            System.out.println("专员审批成功");
            return true;
        }
        System.out.println("专员无法审批");
        return false;
    }

}
