package design.model.chainresponsible.demo1;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className AbstractApproverHandler
 * @description TODO
 * @date 2023/9/22
 */
public abstract class AbstractApproverHandler implements ApproveHandler {
    ApproveHandler next;


    protected Boolean nextApprove(long days) {
        if (next != null) {
            return next.approve(days);
        }
        return false;
    }

    @Override
    public void setNext(ApproveHandler handler) {
        this.next = handler;
    }

    @Override
    public ApproveHandler getNext() {
        return this.next;
    }
}
