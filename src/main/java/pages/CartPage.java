package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class CartPage {
    private WebDriver driver;

    private By cartIcon = By.xpath("//a[@class='shopping_cart_link']");

    private By cartItems = By.xpath("//div[@class='cart_item']");

    private By linkedIn = By.xpath("//a[contains(.,'LinkedIn')]");

    private By faceBook = By.xpath("//a[contains(.,'Facebook')]");

    private By xTwitter = By.xpath("//a[contains(.,'Twitter')]");

    public CartPage (WebDriver driver){
        this.driver = driver;
    }

    public void openCart(){
        driver.findElement(cartIcon).click();
    }

    public int getCartItemsCount(){
       return driver.findElements(cartItems).size();
    }

    public void clickLinkedIn() {
        driver.findElement(linkedIn).click();
    }

    public void clickFacebook() {
        driver.findElement(faceBook).click();
    }

    public void clickXTwitter() {
        driver.findElement(xTwitter).click();
    }

    public void switchToNewTab(String originalWindow) {
        for (String window : driver.getWindowHandles()) {
            if (!window.equals(originalWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }
    }

    public List<String> getCartProductNames() {

        List<WebElement> products = driver.findElements(
                By.className("inventory_item_name")
        );

        List<String> productNames = new ArrayList<>();

        for (WebElement product : products) {
            productNames.add(product.getText());
        }

        return productNames;
    }

    public void removeProduct(String productName) {

        By removeButton = By.xpath(
                "//div[@class='cart_item']" +
                        "[.//div[@data-test='inventory-item-name' and normalize-space()='" + productName + "']]" +
                        "//button"
        );

        driver.findElement(removeButton).click();
    }
}
