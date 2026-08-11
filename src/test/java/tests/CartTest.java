package tests;

import base.BaseTest;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.DataDriven;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class CartTest extends BaseTest {

    DataDriven data = new DataDriven();

    @Test
    public void verifyEmptyCartAndSocialLinks()
            throws IOException, org.json.simple.parser.ParseException {

        JSONObject valid = data.jsonReader("validLogin");

        String username = valid.get("username").toString();
        String password = valid.get("password").toString();

        loginPage.enterData(username, password);
        loginPage.clickOnLoginBtn();

        cartPage.openCart();

        // Verify cart is empty
        Assert.assertEquals(cartPage.getCartItemsCount(), 0);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));


        // LINKEDIN
        String originalWindow = driver.getWindowHandle();

        cartPage.clickLinkedIn();

        for (String window : driver.getWindowHandles()) {
            if (!window.equals(originalWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }

        Assert.assertTrue(driver.getCurrentUrl().contains("linkedin"));

        driver.close();
        driver.switchTo().window(originalWindow);

        // FACEBOOK
//        cartPage.clickFacebook();
//
//        for (String window : driver.getWindowHandles()) {
//            if (!window.equals(originalWindow)) {
//                driver.switchTo().window(window);
//                break;
//            }
//        }
//
//        Assert.assertTrue(
//                driver.getCurrentUrl().contains("facebook")
//        );
//
//        driver.close();
//        driver.switchTo().window(originalWindow);
    }

    @Test
    public void verifyThreeProductsInCart()
            throws IOException, org.json.simple.parser.ParseException {

        JSONObject valid = data.jsonReader("validLogin");

        String username = valid.get("username").toString();
        String password = valid.get("password").toString();

        loginPage.enterData(username, password);
        loginPage.clickOnLoginBtn();

        List<String> products = data.getCartProducts();

        for (String product : products) {
            inventoryPage.addProduct(product);
        }

        cartPage.openCart();

        List<String> actualProducts = cartPage.getCartProductNames();

        Assert.assertEquals(actualProducts, products);
    }

    @Test
    public void removeOneProduct() throws IOException, ParseException {

        JSONObject valid = data.jsonReader("validLogin");

        String username = valid.get("username").toString();
        String password = valid.get("password").toString();

        // Login
        loginPage.enterData(username, password);
        loginPage.clickOnLoginBtn();

        // Get products from JSON
        List<String> products = data.getCartProducts();

        // Add all 3 products
        for (String product : products) {
            inventoryPage.addProduct(product);
        }

        cartPage.openCart();

        cartPage.removeProduct(products.get(1));

        inventoryPage.clickContinue();

        Assert.assertEquals(
                inventoryPage.getProductButtonText(products.get(1)),
                "Add to cart"
        );

        Assert.assertEquals(
                inventoryPage.getProductButtonText(products.get(0)),
                "Remove"
        );

        Assert.assertEquals(
                inventoryPage.getProductButtonText(products.get(2)),
                "Remove"
        );
    }
}