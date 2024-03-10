package pageObjects;

import abstractComponents.AbstractBasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Optional;

public class CartPage extends AbstractBasePage<CartPage> {
    public CartPage(WebDriver driver){
        super(driver, "cart-page.properties");
    }

    public List<WebElement> getAllProductsInCart(){
        List<WebElement> list = driver.findElements(getLocator("allProductsInCart"));
        return list;
    }

    public void checkout(){
        driver.findElement(getLocator("termsOfServiceCheckbox")).click();
        driver.findElement(getLocator("checkout")).click();
    }

    public Optional<WebElement> getProductInCart(String productTitle){
        return getAllProductsInCart().stream().filter(cartItem -> cartItem.findElement(getLocator("productInCart")).getText().equals(productTitle)).findAny();
        //LOG.info(""+cartPage.getAllProductsInCart().stream().anyMatch(cartItem -> cartItem.findElement(By.xpath("//td[@class='product']")).getText().equals("Digital Storm VANQUISH 3 Custom Performance PC")));
    }
}
