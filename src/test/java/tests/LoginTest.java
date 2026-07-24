package tests;

import base.BaseTest;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.DataDriven;

import java.io.IOException;

public class LoginTest extends BaseTest {

    DataDriven data = new DataDriven();

    @Test
    public void verifySuccessfulLogin() throws IOException, ParseException {

        JSONObject valid = data.jsonReader("validLogin");

        String username = valid.get("username").toString();
        String password = valid.get("password").toString();

        loginPage.enterData(username, password);
        loginPage.clickOnLoginBtn();

        Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"));
    }

    @Test
    public void verifyInvalidLogin() throws IOException, ParseException {

        JSONObject invalid = data.jsonReader("invalidLogin");

        String username = invalid.get("username").toString();
        String password = invalid.get("password").toString();

        loginPage.enterData(username, password);
        loginPage.clickOnLoginBtn();

        Assert.assertTrue(loginPage.getErrorMessage().contains("Username and password do not match"));
    }

    @Test
    public void verifyLoginWithoutPassword() throws IOException, ParseException {

        JSONObject empty = data.jsonReader("emptyPassword");

        String username = empty.get("username").toString();
        String password = empty.get("password").toString();

        loginPage.enterData(username, password);
        loginPage.clickOnLoginBtn();

        Assert.assertTrue(loginPage.getErrorMessage().contains("Password is required"));
    }
}