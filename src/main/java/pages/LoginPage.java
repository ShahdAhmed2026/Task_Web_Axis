package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private By email = By.id("email");
    private By password = By.id("pass");
    private By loginButton = By.id("send2");
    private By pageHeader = By.tagName("h1");
    private By errorMessage = By.cssSelector("li.error-msg span");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Enter login email")
    public void enterEmail(String userEmail) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(email)).sendKeys(userEmail);
    }
    @Step("Enter login password")
    public void enterPassword(String userPassword) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(password)).sendKeys(userPassword);
    }

    @Step("Click Login button")
    public void clickLoginButton() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(loginButton));
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    public boolean isLoginSuccessful() {
        return wait.until(ExpectedConditions.urlContains("customer/account"));
    }
    @Step("Login with email and password")
    public void login(String userEmail, String userPassword) {
        enterEmail(userEmail);
        enterPassword(userPassword);
        clickLoginButton();}

    public String getPageHeader() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(pageHeader)).getText();
    }
    public String getErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).getText();
    }


}