package driver;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DriverManager {

    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<WebDriver>(); // each thread accessing DRIVER will have its own copy of WebDriver instance
    private static final Logger LOG = LoggerFactory.getLogger(DriverManager.class);

    public static WebDriver getWebDriver(){
        return DRIVER.get();
    }

    public static void setWebDriver(WebDriver driver){
        DRIVER.set(driver);
    }

    public static void closeDriver(){
        if(DRIVER.get() != null){
            DRIVER.get().quit();
            DRIVER.remove();
        }
    }

}
