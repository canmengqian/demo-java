package design.model.chainresponsible.demo2;

import design.model.chainresponsible.demo1.AbstractApproverHandler;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className ManagerApprover
 * @description 经理审批
 * @date 2023/9/22
 */
public class ManagerHandler implements ApproveHandler {
    @Override
    public Boolean approve(long days) {
        if (days < 10 && days >= 5) {
            System.out.println("经理审批通过,审批天数：" + days);
            return true;
        }
        System.out.println("经理无法审批");
        return false;
    }


}
