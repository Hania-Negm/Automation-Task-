package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private WebDriver driver;

    private By usernameField = By.id("user-name");
    private By passwordField = By.id("password");
    private By loginBtn = By.id("login-button");
    private By errorMsg = By.xpath("//h3[@data-test='error']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPage enterData(String userName, String pass) {
        driver.findElement(usernameField).sendKeys(userName);
        driver.findElement(passwordField).sendKeys(pass);
        return this;
    }

    public void clickOnLoginBtn() {
        driver.findElement(loginBtn).click();
    }

    public String getErrorMessage() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOfElementLocated(errorMsg));

        return driver.findElement(errorMsg).getText();
    }
}