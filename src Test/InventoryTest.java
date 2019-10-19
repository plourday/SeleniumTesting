import org.apache.commons.io.FileUtils;
import org.junit.BeforeClass;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class InventoryTest {

    private List<Vehicle> list = new ArrayList<>();

    public InventoryTest(List<Vehicle> list) {
        this.list = list;
    }

    public void add(Vehicle vehicle) {

        list.add(vehicle);
    }

    public void remove(Vehicle vehicle) {

        list.remove(vehicle);
    }

    public Vehicle findCheapestVehicle() {
        Vehicle lowestVehicle = null;
        for (int i = 0; i < list.size(); i++) {

            Vehicle vehicle = list.get(i);
            double vehiclePrice = vehicle.getPrice();
            if (vehiclePrice <= list.get(i - 1).getPrice()) {
                lowestVehicle = vehicle;
            } else {
                lowestVehicle = list.get(i);
            }
            return lowestVehicle;
        }
        return lowestVehicle;
    }

    public Vehicle findMostExpensiveVehicle() {
        Vehicle highestVehicle = null;
        for (int i = 0; i < list.size(); i++) {

            Vehicle vehicle = list.get(i);
            double vehiclePrice = vehicle.getPrice();
            if (vehiclePrice >= list.get(i - 1).getPrice()) {
                highestVehicle = vehicle;
            } else {
                highestVehicle = list.get(i);
            }
            return highestVehicle;
        }
        return highestVehicle;

    }

    public void printAveragePriceOfAllVehicles() {
        double averagePrice = 0;
        for (int i = 0; i < list.size(); i++) {
            double currentprice = list.get(i).getPrice();
            averagePrice = currentprice + averagePrice;
        }
        System.out.println(averagePrice);

    }


}
