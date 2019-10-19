import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DealershipTest {


    public List<Vehicle> list = new ArrayList<Vehicle>();


    @Test
    public void loadInventoryFromWeb() {

        File file = new File("inventory.csv");
        try {

            FileUtils.copyURLToFile(new URL("https://goo.gl/phaEbQ"), file);
            String cars = FileUtils.readFileToString(file);

            String[] carslist;
            int totalNumberOfLines = StringUtils.countMatches(cars, "\n");

            carslist = StringUtils.split(cars, "\n");
            String make;
            String model;
            int year;
            double price;
            boolean isConvertible;

            for (int i = 0; i <= totalNumberOfLines; i++) {
                System.out.println(carslist[i]);

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

                list.add(new Vehicle(make, model, year, price, isConvertible));
            }

            for (int i = 0; i <= list.size() - 1; i++) {
                System.out.println(list.get(i).getModel());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        //Various tests to ensure the list was populated correctly.
        Assert.assertEquals(324, file.length(), 1);
        Assert.assertEquals("Model X", list.get(0).getModel());
        Assert.assertEquals("Ferrari", list.get(2).getMake());
        Assert.assertEquals(2017, list.get(3).getYear());
        Assert.assertEquals(360000, list.get(5).getPrice(), 5.0);
        Assert.assertEquals(true, list.get(8).isIs4WD());

    }
}