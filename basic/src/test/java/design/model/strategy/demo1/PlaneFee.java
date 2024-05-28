package design.model.strategy.demo1;

public class PlaneFee implements Fee{
    @Override
    public Double getFee(Double distance) {
        if(distance >0 && distance<100){
            return  200D;
        }
        if(distance>=100 &&  distance <500){
            return  400D;
        }

        return 1000D;
    }

    @Override
    public String getType() {
        return "飞机";
    }
}
