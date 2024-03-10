package pageObjects;

import abstractComponents.AbstractBasePage;
import constants.MenuItem;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
public class NavigationMenu extends AbstractBasePage<NavigationMenu> {

    public NavigationMenu(WebDriver driver){
        super(driver, "navigationMenu.properties");
    }

    public void goToCartPage(){
        driver.findElement(getLocator("cart")).click();
    }

    public void openComputersProductCatalog(MenuItem catalogName){
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(getLocator("computers"))).perform();

        switch (catalogName){
            case DESKTOPS:
                driver.findElement(getLocator("desktopCatalogLink")).click();
                break;
            case NOTEBOOKS:
                driver.findElement(getLocator("notebookCatalogLink")).click();
                break;
        }
    }
}
