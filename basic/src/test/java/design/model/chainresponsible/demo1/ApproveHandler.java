package design.model.chainresponsible.demo1;

/**
 * @author zhengzz
 * @version 1.0.0
 * @description 抽象审批处理器
 * @date 2023/9/22
 */
public interface ApproveHandler {
    Boolean approve(long days);

    void setNext(ApproveHandler handler);

    ApproveHandler getNext();
}
