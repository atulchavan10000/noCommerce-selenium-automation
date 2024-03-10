package noCommerce.tests;

import driver.DriverFactory;
import driver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.ConfigurationManager;
import utils.DataManager;

import javax.security.auth.login.Configuration;
import java.lang.reflect.Method;

public class BaseTest {
    private static final String DEFAULT_BROWSER = System.getProperty("browser", "chrome");
    static Logger LOG = null;

    WebDriver driver;
    String webUrl;

    String userEmail;
    String userPassword;
    public BaseTest(){
        LOG = LoggerFactory.getLogger(getClass());
        webUrl = ConfigurationManager.getInstance().getProperty("webUrl");
        userEmail = DataManager.getInstance().getString("userEmail");
        userPassword = DataManager.getInstance().getString("userPassword");
    }

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
}
