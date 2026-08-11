package tests;

import base.BaseTest;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.DataDriven;

import java.io.IOException;

public class InventoryTest extends BaseTest {

    DataDriven data = new DataDriven();

    @Test
    public void verifyInventoryPageElements() throws IOException, org.json.simple.parser.ParseException {

        JSONObject valid = data.jsonReader("validLogin");

        String username = valid.get("username").toString();
        String password = valid.get("password").toString();

        loginPage.enterData(username, password);
        loginPage.clickOnLoginBtn();

        // Verify Inventory Page
        Assert.assertEquals(inventoryPage.getTitle(), "Swag Labs");
        Assert.assertTrue(inventoryPage.isCartDisplayed());
        Assert.assertEquals(inventoryPage.getProductsCount(), 6);
    }
}
