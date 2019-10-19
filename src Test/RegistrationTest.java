import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class RegistrationTest {

    private static WebDriver driver;

    @BeforeClass
    public static void setUp() {
        driver = new FirefoxDriver();
    }

    @Test
    public void testIHGWebsite() {

        driver.get("https://ihgrewardsclubdining.rewardsnetwork.com/join.htm");

        //Click First Name Box, Input Roger as a name
        WebElement firstname = driver.findElement(By.id("firstName"));
        firstname.clear();
        firstname.sendKeys("Roger");

        //Click Last Name Box, Input John as a name
        WebElement lastname = driver.findElement(By.id("lastName"));
        lastname.clear();
        lastname.sendKeys("John");

        //Click Zipcode Box, Input 77777
        WebElement zip = driver.findElement(By.id("zipcode"));
        zip.clear();
        zip.sendKeys("77777");

        //Click Email Address Box, Submit blahblah@hotmail.com
        WebElement email = driver.findElement(By.id("email"));
        email.clear();
        email.sendKeys("blahblah@hotmail.com");

        //Click emailConfirm Box, submit blahblah@hotmail.com
        WebElement emailConfirm = driver.findElement(By.id("emailConfirm"));
        emailConfirm.clear();
        emailConfirm.sendKeys("blahblah@hotmail.com");

        //Click Accept Terms CheckBox.
        WebElement acceptTermsBox = driver.findElement(By.id("acceptDFFTerms1"));
        acceptTermsBox.click();

        //Click Next Button to proceed to next page.
        WebElement nextButton = driver.findElement(By.cssSelector("input.f_left"));
        nextButton.click();

        Assert.assertEquals("Next Button was clicked, Checked URL", "https://ihgrewardsclubdining.rewardsnetwork.com/enrollment.htm", driver.getCurrentUrl());
    }

}
