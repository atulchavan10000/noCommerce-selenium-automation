package noCommerce.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.*;

import java.time.Duration;

import static constants.MenuItem.DESKTOPS;

public class PurchaseProductTest extends BaseTest{

        @Test
        public void purchaseProductTest() {


            // login to application
            LoginPage loginPage = new LoginPage(driver);
            driver.get(webUrl);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            loginPage.login(userEmail, userPassword);

            // after login, it will go to home page and we  will click on Computers -> desktops
            NavigationMenu navMenu = new NavigationMenu(driver);
            navMenu.openComputersProductCatalog(DESKTOPS);

            // now you will be on Desktops product catalog page, open the product by clicking on it and add it to cart
            ProductCatalogPage productCatalogPage = new ProductCatalogPage(driver);
            productCatalogPage.gotoProduct("Digital Storm VANQUISH 3 Custom Performance PC");

            // on product page
            ProductPage productPage = new ProductPage(driver);
            productPage.addToCart();

            // go to cart page and verify that your product is there in the list of products in cart
            navMenu.goToCartPage();

            CartPage cartPage = new CartPage(driver);

            Assert.assertTrue(cartPage.getProductInCart("Digital Storm VANQUISH 3 Custom Performance PC").isPresent());

            cartPage.checkout();
        }
}
