package tests;

import io.qameta.allure.*;
import io.qameta.allure.testng.AllureTestNg;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.ConfigReader;

@Feature("User Login")
@Listeners({AllureTestNg.class})
public class LoginTest extends BaseTest {
    @Test
    @Description("Verify that user can login successfully with valid credentials")
    public void LoginSuccessfully() {
        driver.get(Login_Url);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(
                ConfigReader.get("validEmail"),
                ConfigReader.get("validPassword")
        );
        Assert.assertEquals(loginPage.getPageHeader(), "MY DASHBOARD");
    }




    @Test
    @Description("Verify error message is displayed when user logs in with invalid credentials(Wrong Password)")
    public void InvalidLogin() {
        driver.get(Login_Url);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(
                ConfigReader.get("validEmail"),
                ConfigReader.get("invalidPassword")
        );
        Assert.assertTrue(
                loginPage.getErrorMessage().contains("Invalid login or password")
        );
    }
}