package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DriverFactory {
    private static final Logger LOG = LoggerFactory.getLogger(DriverFactory.class);

    private DriverFactory(){

    }

    /*
    * Create WebDriver Instance
    * */
    public static WebDriver createInstance(String browser){
        WebDriver driver = null;
        switch (browser){
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                LOG.info("Using Fireforxdriver");
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                LOG.info("Using Edgedriver");
                break;
            default:
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                LOG.info("Using Chromedriver");
                break;

         }

         return driver;
    }
}
