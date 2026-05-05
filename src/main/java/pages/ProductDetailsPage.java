package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class ProductDetailsPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private By productName = By.cssSelector(".product-shop .product-name .h1");
    private By addToCart = By.cssSelector("button[title='Add to Cart']");
    private By colorRequiredMessage = By.id("advice-required-entry-attribute92");
    private By sizeRequiredMessage = By.id("advice-required-entry-attribute186");


    public ProductDetailsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    public String getProductName() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(productName)).getText();
    }

    @Step("Select color Black")
    public void selectColor(String color) {
        By colorLocator = By.cssSelector("#configurable_swatch_color a[title='" + color + "']");

        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(colorLocator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    @Step("Select size")
    public void selectSize(String size) {
        By sizeLocator = By.cssSelector("#configurable_swatch_shoe_size a[title='" + size + "']");
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(sizeLocator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    @Step("Add to Cart")
    public void addToCart() {
        WebElement button = wait.until(ExpectedConditions.presenceOfElementLocated(addToCart));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", button);
        ((JavascriptExecutor) driver).executeScript("productAddToCartForm.submit(arguments[0]);", button);

        wait.until(ExpectedConditions.or(
                ExpectedConditions.urlContains("checkout/cart"),
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".success-msg span"))
        ));


    }

    @Step("Click Add to Cart for validation")
    public void clickAddToCartForValidation() {
        WebElement button = wait.until(ExpectedConditions.presenceOfElementLocated(addToCart));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", button);
        ((JavascriptExecutor) driver).executeScript("productAddToCartForm.submit(arguments[0]);", button);
        wait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOfElementLocated(colorRequiredMessage),
                ExpectedConditions.visibilityOfElementLocated(sizeRequiredMessage)
        ));
    }

    public String getColorRequiredMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(colorRequiredMessage)).getText();
    }

    public String getSizeRequiredMessage() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(sizeRequiredMessage)).getText();
    }
}