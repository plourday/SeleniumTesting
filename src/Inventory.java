import java.util.List;

public class Inventory {

    private List<Vehicle> list;

    public Inventory(List<Vehicle> list) {
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
