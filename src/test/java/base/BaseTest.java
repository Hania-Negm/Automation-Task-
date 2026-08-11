package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.CartPage;
import pages.InventoryPage;
import pages.LoginPage;
import org.testng.annotations.*;

public class BaseTest {
    protected WebDriver driver;
    protected LoginPage loginPage;
    protected InventoryPage inventoryPage;
    protected CartPage cartPage;

    @BeforeMethod
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");

        // LOGIN OBJECT
        loginPage = new LoginPage(driver);

        // INVENTORY PAGE
        inventoryPage = new InventoryPage(driver);

        //CART PAGE
        cartPage = new CartPage(driver);
    }

//    @AfterMethod
//    public void terminate(){
//        driver.quit();
//    }
}
