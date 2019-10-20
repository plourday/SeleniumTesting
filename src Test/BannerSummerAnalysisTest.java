import com.thoughtworks.xstream.XStream;
import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class BannerSummerAnalysisTest {

    private static WebDriver driver;
    private ArrayList<String> list = new ArrayList<>();
    private ArrayList<String> xmlList = new ArrayList<>();
    private ArrayList<item> formatList = new ArrayList<>();


    @BeforeClass
    public static void
    setUpChrome() {
        System.setProperty("webdriver.chrome.driver", "/Users/andrewplourde/Desktop/chromedriver");
        driver = new ChromeDriver();
    }

    @AfterClass
    public static void cleanUp() {
        driver.close();

    }


    @Test
    public void createXMLFile() {

        //Create an xml file
        File file = new File("xmlfileforanalysis.xml");

        //Using a third party Library to format strings into xml data.
        XStream xstream = new XStream();

        int year = 2010;
        //For loop to get the strings from each year into an array
        for (int i = 0; i <= xmlList.size() - 1; i++) {
            formatList.add(new item(year + i, Integer.parseInt(xmlList.get(i))));
        }

        String xml = "";
        //setting up the xml String itself
        for (int i = 0; i <= xmlList.size() - 1; i++) {
            xml = xml + "\n" + xstream.toXML(formatList.get(i));
        }
        //Default xml header and footter to be tacked on to the formatted xml Strings
        String defaultxmlProperties = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "\t<data version=\"1.0\">";
        String xmlClose = "</data>" + "\n";

        try {
            //Using Apache FileUtils-Write the xml string to file with the tacked on strings.
            FileUtils.writeStringToFile(file, defaultxmlProperties + xml + xmlClose);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Tests to check if there are students registered for each summer semester in each year.
        Assert.assertEquals(1001, Double.parseDouble(xmlList.get(0)), 1000);
        Assert.assertEquals(1001, Double.parseDouble(xmlList.get(1)), 1000);
        Assert.assertEquals(1001, Double.parseDouble(xmlList.get(2)), 1000);
        Assert.assertEquals(1001, Double.parseDouble(xmlList.get(3)), 1000);
        Assert.assertEquals(1001, Double.parseDouble(xmlList.get(4)), 1000);
        Assert.assertEquals(1001, Double.parseDouble(xmlList.get(5)), 1000);
        Assert.assertEquals(1001, Double.parseDouble(xmlList.get(6)), 1000);
        Assert.assertEquals(1001, Double.parseDouble(xmlList.get(7)), 1000);
        Assert.assertEquals(1001, Double.parseDouble(xmlList.get(8)), 1000);
        Assert.assertEquals(1001, Double.parseDouble(xmlList.get(9)), 1000);
    }


    @Test
    //Large For loop that runs through the GGC Banner Website and scrapes each Summer IT course for the number of students
    // that were registered for that particular course.
    public void analysis() {
        //Ten iterations for 2010 - 2019
        for (int i = 1; i <= 10; i++) {
            //Set an iterator so the Driver can find the year to select in the dropdown
            int iterator = 1;
            driver.get("https://ggc.gabest.usg.edu/pls/B400/bwckschd.p_disp_dyn_sched");
            //Navigates the first dropdown menu
            Select InitialDropdown = new Select(driver.findElement(By.xpath("/html/body/div[3]/form/table/tbody/tr/td/select")));
            //finds the submit button
            WebElement InitialSubmission = driver.findElement(By.xpath("/html/body/div[3]/form/input[2]"));
            //Selects a year for Summer "year" (View Only) and click
            InitialDropdown.selectByVisibleText("Summer " + (i + 2009) + " " + "(View only)");
            InitialSubmission.click();
            //Create a selector to select IT everytime it returns to this page.
            Select ITSelector = new Select(driver.findElement(By.id("subj_id")));
            ITSelector.selectByVisibleText("Information Technology");
            //Select Submit button and click
            WebElement secondSubmission = driver.findElement(By.xpath("/html/body/div[3]/form/input[12]"));
            secondSubmission.click();

            try {
                //So while there are still classes to be chosen on the page...
                while (driver.findElement(By.xpath("/html/body/div[3]/table[1]/tbody/tr[" + iterator + "]/th/a")).isDisplayed()) {
                    // Create a webelement for the class based on the iterator initialized above.
                    WebElement classSelector = driver.findElement(By.xpath("/html/body/div[3]/table[1]/tbody/tr[" + iterator + "]/th/a"));
                    classSelector.click();
                    //Gets the value of the actual number of students registered for that course.
                    WebElement Actual = driver.findElement(By.xpath("/html/body/div[3]/table[1]/tbody/tr[2]/td/table/tbody/tr[2]/td[2]"));
                    list.add(Actual.getText());
                    //Steps back a page.
                    //As it turns out, you CANNOT set this to a specific URL. The URL for the particular webpage creates from selecting
                    // the dropdowns on the search page is not recreateable by putting it's address in the search bar.
                    //Hence driver.navigate.back
                    driver.navigate().back();

                    //The webpage is structured with a discernable pattern between each class listing on the page.
                    // Every class, the row is increased by two in the xpath.
                    //Therefore iterator + 2
                    iterator = iterator + 2;
                }
                //Once there are no more classes to be displayed, A nosuchelementexception will be thrown
            } catch (NoSuchElementException e) {
                //Set a total number of students
                int total = 0;
                //A string value of the total number of students
                String xmlTotal = "";
                //Set Previous List as the j
                for (int j = 0; j <= list.size() - 1; j++) {
                    total = (total + Integer.parseInt(list.get(j)));
                    xmlTotal = Integer.toString(total);
                }
                //Adds the number of students to a list to later be stored in an XML file.
                xmlList.add(xmlTotal);
                //Clear the list so that the next list will be for the next year in that iteration I.E the last list had a size of 22 for 2010,
                //The next one will have something else for 2011
                list.clear();
                //Continue the for loop
                continue;
            }
        }
        //Creates the XML File with the DAta aquired from the URL.
        createXMLFile();
        Assert.assertEquals(10, xmlList.size(), 0);
    }


}

//Helper class for the external library
class item {
    private int number;
    private int year;

    item(int year, int number) {
        this.number = number;
        this.year = year;
    }
}

