
public class Truck extends Vehicle {
    private boolean hasSideStep;
    private int towCapacity;

    public Truck(String make, String model, int year, boolean is4WD, double price, double mpg, boolean hasSideStep, int towCapacity) {
        super(make, model, year, price, is4WD);
        this.hasSideStep = hasSideStep;
        this.towCapacity = towCapacity;
    }

    public boolean isHasSideStep() {
        return hasSideStep;
    }

    public int getTowCapacity() {
        return towCapacity;
    }

    public void setHasSideStep(boolean hasSideStep) {
        this.hasSideStep = hasSideStep;
    }

    public void setTowCapacity(int towCapacity) {
        this.towCapacity = towCapacity;
    }

    @Override
    public void printVehicle() {
        System.out.println("Vehicle type: Truck");
        super.printVehicle();
        String str = this.isHasSideStep() == true ? "Yes" : "No";
        System.out.println(str + " Side Step");
        System.out.println("Tow up to " + this.towCapacity + " tons.");
    }

}