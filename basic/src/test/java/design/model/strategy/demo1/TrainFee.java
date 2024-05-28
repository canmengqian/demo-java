package design.model.strategy.demo1;

public class TrainFee implements Fee{
    @Override
    public Double getFee(Double distance) {
        if(distance >0 && distance<100){
            return  50D;
        }
        if(distance>=100 &&  distance <500){
            return  100D;
        }
        if(distance>=500 & distance<1000){
            return  200D;
        }

        return 300D;
    }

    @Override
    public String getType() {
        return "火车";
    }
}
