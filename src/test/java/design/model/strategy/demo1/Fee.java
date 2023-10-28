package design.model.strategy.demo1;

public interface Fee {
    Double getFee(Double distance);

    String getType();
}
