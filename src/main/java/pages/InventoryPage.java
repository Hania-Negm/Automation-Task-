package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class InventoryPage{
    private WebDriver driver;
    private By cartIcon = By.className("shopping_cart_link");
    private By products = By.className("inventory_item");
    private By continueShopping = By.id("continue-shopping");

    public InventoryPage(WebDriver driver){
        this.driver = driver;
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public boolean isCartDisplayed() {
        return driver.findElement(cartIcon).isDisplayed();
    }

    public int getProductsCount() {
        return driver.findElements(products).size();
    }

    public void addProduct(String productName) {

        By addButton = By.xpath(
                "//div[@data-test='inventory-item'][.//div[@data-test='inventory-item-name' and normalize-space()='"
                        + productName + "']]//button"
        );

        driver.findElement(addButton).click();
    }

    public String getProductButtonText(String productName) {

        By productButton = By.xpath(
                "//div[@data-test='inventory-item']" +
                        "[.//div[@data-test='inventory-item-name' and normalize-space()='" + productName + "']]" +
                        "//button"
        );

        return driver.findElement(productButton).getText();
    }

    public void clickContinue(){
        driver.findElement(continueShopping).click();
    }
}
