package tests;

import io.qameta.allure.*;
import io.qameta.allure.testng.AllureTestNg;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.RegisterPage;
import utils.ConfigReader;

@Feature("User Registration")
@Listeners({AllureTestNg.class})
public class RegisterTest extends BaseTest {

    @Test
    @Description("Verify that user can register successfully with valid data")
    public void Veifyregister() {
        driver.get(Register_Url);
        RegisterPage registerPage = new RegisterPage(driver);
        //dynamic naming 3shan kol mara lazm email mo5tlf
        String email = "test" + System.currentTimeMillis() + "@gmail.com";

        registerPage.register(
                ConfigReader.get("firstName"),
                ConfigReader.get("middleName"),
                ConfigReader.get("lastName"),
                email,
                ConfigReader.get("registerPassword")
        );
        Assert.assertEquals(registerPage.getPageHeaderText(), "MY DASHBOARD");
    }

    @Test
    @Description("Verify that Middle name is optional and user can register successfully without providing it")
    public void VerifyRegisterWithoutMiddleName() {
        driver.get(Register_Url);
        RegisterPage registerPage = new RegisterPage(driver);
      //dynamic naming 3shan kol mara lazm email mo5tlf
      String email = "test" + System.currentTimeMillis() + "@gmail.com";
        registerPage.RegisterWithoutMiddleName(
                ConfigReader.get("firstName"),
                ConfigReader.get("lastName"),
                email,
                ConfigReader.get("registerPassword")
        );
        Assert.assertEquals(registerPage.getPageHeaderText(), "MY DASHBOARD");
    }

    @Test
    @Description("Verify invalid email format validation message is displayed")
    public void verifyInvalidEmailFormat() {
        driver.get(Register_Url);
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.register(
                ConfigReader.get("firstName"),
                ConfigReader.get("middleName"),
                ConfigReader.get("lastName"),
                ConfigReader.get("invalidEmail"),
                ConfigReader.get("registerPassword")
        );
        Assert.assertTrue(
                registerPage.getEmailValidationMessage().contains("@")
        ); }

}