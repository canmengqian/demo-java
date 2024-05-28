package design.model.strategy.demo1;

public class TaxiFee implements Fee{
    @Override
    public Double getFee(Double distance) {
        if(distance >0 && distance<100){
            return  300D;
        }
        if(distance>=100 &&  distance <500){
            return  1000D;
        }
        if(distance>=500 & distance<1000){
            return  2000D;
        }

        return 5000D;
    }

    @Override
    public String getType() {
        return "出租车";
    }
}
