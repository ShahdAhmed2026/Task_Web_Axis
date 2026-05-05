package tests;

import io.qameta.allure.*;
import io.qameta.allure.testng.AllureTestNg;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.RegisterPage;

@Feature("User Registration")
@Listeners({AllureTestNg.class})
public class RegisterTest extends BaseTest {

    @Test
    @Description("Verify that user can register successfully with valid data")
    @Severity(SeverityLevel.CRITICAL)
    public void Veifyregister() {
        driver.get(Register_Url);
        RegisterPage registerPage = new RegisterPage(driver);
        //dynamic naming 3shan kol mara lazm email mo5tlf
        String email = "test" + System.currentTimeMillis() + "@gmail.com";

        registerPage.register(
                "Abdelrahman",
                "Ahmed",
                "Ali",
                email,
                "Password222"
        );
        Assert.assertEquals( driver.findElement(By.tagName("h1")).getText(), "MY DASHBOARD" );
    }

    @Test
    @Description("Verify that Middle name is optional and user can register successfully without providing it")
    @Severity(SeverityLevel.CRITICAL)
    public void VerifyRegisterWithoutMiddleName() {
        driver.get(Register_Url);
        RegisterPage registerPage = new RegisterPage(driver);
                             //dynamic naming 3shan kol mara lazm email mo5tlf
      String email = "test" + System.currentTimeMillis() + "@gmail.com";

        registerPage.RegisterWithoutMiddleName(
                "Shahd",
                "Ahmed",
                email,
                "Password111"
        );
        Assert.assertEquals( driver.findElement(By.tagName("h1")).getText(), "MY DASHBOARD" );
    }

    @Test
    @Description("Verify invalid email format validation message is displayed")
    public void verifyInvalidEmailFormat() {
        driver.get(Register_Url);
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.register(
                "Shahd",
                "Ali",
                "Ahmed",
                "invalidEmail",
                "Password123"
        );
        Assert.assertTrue(
                registerPage.getEmailValidationMessage().contains("@")
        ); }



}