package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    //--All Urls--//
    protected String Register_Url = "https://ecommerce.tealiumdemo.com/customer/account/create/";
    protected String Login_Url = "https://ecommerce.tealiumdemo.com/customer/account/login/";


    // ------ //
    protected WebDriver driver;
    @BeforeMethod
    @Step("Setting up the WebDriver")
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    @AfterMethod
    @Step("Tearing down the WebDriver")
    public void tearDown() {
        driver.quit();
    }
}