import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Dealership {
    //Initialized a List
    public List<Vehicle> list = new ArrayList<Vehicle>();

    public void loadInventoryFromWeb(String url) {
        //Create a File and pathname
        File file = new File("inventory.csv");

        try {
            //Use Apache to copy the URL to a file and then read it into a single string.
            FileUtils.copyURLToFile(new URL(url), file);
            String cars = FileUtils.readFileToString(file);


            int totalNumberOfLines = StringUtils.countMatches(cars, "\n");
            //Break the String into an array of Strings
            String[] carslist = StringUtils.split(cars, "\n");
            //Declare variables for parsing the CSV
            String make;
            String model;
            int year;
            double price;
            boolean isConvertible;
            //For loop based on the number of lines in the file
            for (int i = 0; i <= totalNumberOfLines; i++) {
                System.out.println(carslist[i]);
                //Various substring to separate each portion of the vehicle object to it's respective variable.
                make = carslist[i].substring(1, carslist[i].indexOf(" ", 1));

                model = carslist[i].substring(carslist[i].indexOf(" ") + 1, carslist[i].indexOf(","));

                String postModel = carslist[i].substring(carslist[i].indexOf(","));

                year = Integer.parseInt(postModel.substring(1, postModel.indexOf(",", 1)));

                String postYear = postModel.substring(5);

                price = Double.parseDouble(postYear.substring(1, postYear.indexOf(",", 1)));

                if (postYear.contains("TRUE")) {
                    isConvertible = true;
                } else {
                    isConvertible = false;
                }
                //Add a new Vehicle object to the list based on the above variables.
                list.add(new Vehicle(make, model, year, price, isConvertible));
            }
            //Populate the inventory with the newly made list.
            Inventory inventory = new Inventory(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
