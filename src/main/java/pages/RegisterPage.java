package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class RegisterPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private By firstName = By.id("firstname");
    private By middleName = By.id("middlename");  //optional
    private By lastName = By.id("lastname");
    private By email = By.id("email_address");
    private By password = By.id("password");
    private By confirmPassword = By.id("confirmation");
    private By registerButton = By.cssSelector("button[title='Register']");
    private By pageHeader = By.tagName("h1");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Enter first name")
    public void enterFirstName(String fName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstName)).sendKeys(fName);
    }

    @Step("Enter middle name")
    public void enterMiddleName(String mName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(middleName)).sendKeys(mName);
    }

    @Step("Enter last name")
    public void enterLastName(String lName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(lastName)).sendKeys(lName);
    }

    @Step("Enter email")
    public void enterEmail(String userEmail) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(email)).sendKeys(userEmail);
    }

    @Step("Enter password")
    public void enterPassword(String pass) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(password)).sendKeys(pass);
    }

    @Step("Enter confirm password")
    public void enterConfirmPassword(String pass) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(confirmPassword)).sendKeys(pass);
    }

    @Step("Click Register button")
    public void clickRegister() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
                driver.findElement(registerButton));
        wait.until(ExpectedConditions.elementToBeClickable(registerButton)).click();
    }

    @Step("Register with all required user data (without middle name)")
    public void RegisterWithoutMiddleName(String fName, String lName, String userEmail, String pass) {
        enterFirstName(fName);
        enterLastName(lName);
        enterEmail(userEmail);
        enterPassword(pass);
        enterConfirmPassword(pass);
        clickRegister();
    }

    @Step("Register with user data (middle name) ")
    public void register(String fName, String middleName, String lName, String userEmail, String pass) {
        enterFirstName(fName);
        enterMiddleName(middleName);
        enterLastName(lName);
        enterEmail(userEmail);
        enterPassword(pass);
        enterConfirmPassword(pass);
        clickRegister();
    }

    @Step("Get email validation message")
    public String getEmailValidationMessage() {
        WebElement emailElement = wait.until(ExpectedConditions.visibilityOfElementLocated(email));
        return emailElement.getAttribute("validationMessage");
    }

    @Step("Get page header text")
    public String getPageHeaderText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(pageHeader)).getText();
    }
}