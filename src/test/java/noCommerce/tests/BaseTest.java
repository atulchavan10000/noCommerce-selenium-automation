package noCommerce.tests;

import driver.DriverFactory;
import driver.DriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.ConfigurationManager;
import utils.DataManager;

import javax.security.auth.login.Configuration;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

public class BaseTest {
    private static final String DEFAULT_BROWSER = System.getProperty("browser", "chrome");
    static Logger LOG = null;

    public WebDriver driver;
    String webUrl;

    String userEmail;
    String userPassword;
    public BaseTest(){
        LOG = LoggerFactory.getLogger(getClass());
        webUrl = ConfigurationManager.getInstance().getUrl("webUrl");
        userEmail = DataManager.getInstance().getString("userEmail");
        userPassword = DataManager.getInstance().getString("userPassword");
    }

    /*
    *  Checks if the current thread has the WebDriver instance.
    *  If it doesnt exist, we create an instance by using DriverFactory
    *  otherwise assigns the existing instance to "driver"
    * */
    @BeforeMethod
    public void setUp(Method method) {
        if (DriverManager.getWebDriver() == null) {
            DriverManager.setWebDriver(DriverFactory.createInstance(DEFAULT_BROWSER));
            LOG.info("Using the driver: " + DriverManager.getWebDriver());
            driver = DriverManager.getWebDriver();
        }
    }

    @AfterMethod
    public void closeDriver(){
         DriverManager.closeDriver();
    }

    public String getScreenshot(String testCaseName, WebDriver driver) {
        TakesScreenshot ts = (TakesScreenshot)driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File dest = new File(System.getProperty("user.dir") + "\\reports\\" + testCaseName + ".png");
        try {
            FileUtils.copyFile(source, dest);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return testCaseName + ".png";
    }

}
