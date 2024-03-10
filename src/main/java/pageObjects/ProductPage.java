package pageObjects;

import abstractComponents.AbstractBasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductPage extends AbstractBasePage<ProductPage> {
            public ProductPage(WebDriver driver){
                super(driver, "product-page.properties");
            }
            public void addToCart() {
                driver.findElement(getLocator("addToCartButton")).click();
            }
}
