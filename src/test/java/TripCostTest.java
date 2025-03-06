
import org.example.TripCost;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TripCostTest {

    @Test
    public void testCalculateCost() {
        TripCost tripCost = new TripCost();
        double distance = 100.0;
        double fuelPrice = 1.5;
        double standardConsumption = 5.0;
        double expectedCost = 7.5; // (100 / 5) * 1.5
        double actualCost = tripCost.calculateCost(distance, fuelPrice, standardConsumption);
        assertEquals(expectedCost, actualCost, 0.001);
    }

    @Test
    public void testFuelNeeded() {
        TripCost tripCost = new TripCost();
        double distance = 100.0;
        double standardConsumption = 5.0;
        double expectedFuelNeeded = 5.0; // 100 / 5
        double actualFuelNeeded = tripCost.fuelNeeded(distance, standardConsumption);
        assertEquals(expectedFuelNeeded, actualFuelNeeded, 0.001);
    }
}