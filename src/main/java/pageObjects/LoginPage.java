package pageObjects;

import abstractComponents.AbstractBasePage;
import org.openqa.selenium.WebDriver;

public class LoginPage extends AbstractBasePage<LoginPage> {

            public LoginPage(WebDriver driver){
                super(driver, "login-page.properties");
            }

            public void login(String email, String password){
                driver.findElement(getLocator("userEmail")).sendKeys(email);
                driver.findElement(getLocator("userPassword")).sendKeys(password);
                driver.findElement(getLocator("loginButton")).click();
             }
}
