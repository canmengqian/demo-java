package design.model.chainresponsible.demo2;

import java.util.LinkedList;
import java.util.List;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className ApproveManager
 * @description TODO
 * @date 2023/9/22
 */
public class ApproveManager implements ApproveHandler {
    private List<ApproveHandler> APPROVE_CHAINS = new LinkedList<>();

    public static ApproveManager instance() {
        return new ApproveManager();
    }

    public ApproveManager add(ApproveHandler handler) {
        this.APPROVE_CHAINS.add(handler);
        return this;
    }

    @Override
    public Boolean approve(long days) {
        for (ApproveHandler handler : APPROVE_CHAINS) {
            if (handler.approve(days)) {
                return true;
            }
        }
        System.out.println("无法处理该审批");
        return false;
    }
}
