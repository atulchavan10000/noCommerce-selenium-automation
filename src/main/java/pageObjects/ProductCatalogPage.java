package pageObjects;

import abstractComponents.AbstractBasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCatalogPage extends AbstractBasePage<ProductCatalogPage> {

            public ProductCatalogPage(WebDriver driver){
                super(driver, "productCatalog-page.properties");
            }

            public void gotoProduct(String productTitle){
                WebElement elementToBuy = driver.findElements(getLocator("allProducts")).stream().filter(product ->
                        product.getText().equalsIgnoreCase(productTitle)).findFirst().orElse(null);
                elementToBuy.click();
            }
}
