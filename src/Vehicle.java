

public class Vehicle {
    private String make;
    private String model;
    private int year;
    private boolean is4WD;
    private double price;
    private double mpg;

    public Vehicle(String make, String model, int year, double price, boolean is4WD) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.is4WD = is4WD;
        this.price = price;
        this.mpg = mpg;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public boolean isIs4WD() {
        return is4WD;
    }

    public void setIs4WD(boolean is4WD) {
        this.is4WD = is4WD;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public void printVehicle(){
        System.out.println("Make: " + make + "\n" + "Model: " + model + "\n" + "Year: " + year + "\n"
                 + "\n" + "Price:" + "$" + price + "\n" + "4WD? " + is4WD + "\n");
    }
}