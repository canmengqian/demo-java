package design.model.chainresponsible.demo2;

/**
 * @author zhengzz
 * @version 1.0.0
 * @description 抽象审批处理器
 * @date 2023/9/22
 */
public interface ApproveHandler {
    Boolean approve(long days);
}
