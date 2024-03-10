package noCommerce.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.*;

import java.time.Duration;

import static constants.MenuItem.DESKTOPS;

public class LoginTest extends BaseTest{

    @Test
    public void purchaseProductTest() throws InterruptedException {


        // login to application
        LoginPage loginPage = new LoginPage(driver);
        driver.get(webUrl);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        loginPage.login(userEmail, userPassword);

        Thread.sleep(10000);
        // after login, it will go to home page, verify its a home page
        Assert.assertEquals(driver.getTitle(), "nopCommerce demo store");
    }
}
