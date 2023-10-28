package design.model.strategy.demo1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChinaTravelTest {

    @Test
    void getFee() {
        ChinaTravel chinaTravel = new ChinaTravel ("北京","长沙",1400d,new PlaneFee ());
        chinaTravel.getFee ();

         chinaTravel = new ChinaTravel ("北京","长沙",1400d,new TrainFee ());
        chinaTravel.getFee ();

         chinaTravel = new ChinaTravel ("北京","长沙",1400d,new TaxiFee ());
        chinaTravel.getFee ();
    }
}