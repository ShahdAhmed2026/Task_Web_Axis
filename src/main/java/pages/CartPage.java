package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class CartPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By successMessage = By.cssSelector(".success-msg span");
    private By cartHeader = By.cssSelector(".page-title h1");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public String getSuccessMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage)).getText().trim();
    }

    public String getHeader() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(cartHeader)).getText().trim();
    }
}