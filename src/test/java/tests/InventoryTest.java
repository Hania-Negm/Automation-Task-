package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.*;

public class InventoryTest extends BaseTest {

    @Test
    public void verifyInventoryPageElements(){
        loginPage.enterData("standard_user", "secret_sauce");
        loginPage.clickOnLoginBtn();

        Assert.assertEquals(inventoryPage.getTitle(),"Swag Labs");
        Assert.assertTrue(inventoryPage.isCartDisplayed());
        Assert.assertEquals(inventoryPage.getProductsCount(),6);
    }
}
