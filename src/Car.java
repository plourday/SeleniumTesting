public class Car extends Vehicle {

    private boolean isConvertible;

    public Car(String make, String model, int year, boolean is4WD, double price, double mpg, boolean isConvertible) {
        super(make, model, year, price, is4WD);
    }

    public boolean isConvertible() {
        return isConvertible;
    }

    public void setConvertible(boolean convertible) {
        isConvertible = convertible;
    }


    @Override
    public void printVehicle() {
        System.out.println("Vehicle type: Car");
        super.printVehicle();
        String str;
        if (this.isConvertible) str = "yes";
        else str = "No";
        System.out.println("Convertible? : " + this.isConvertible + "\n");
    }
}
