package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class ProductDetailsPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private By productName = By.cssSelector(".product-shop .product-name .h1");
    private By size10 = By.cssSelector("#configurable_swatch_shoe_size a[title='10']");
    private By selectedSize10 = By.cssSelector("#configurable_swatch_shoe_size li.selected a[title='10']");
    private By colorBlack = By.cssSelector("#configurable_swatch_color a[title='Black']");
    private By selectedColorBlack = By.cssSelector("#configurable_swatch_color li.selected a[title='Black']");
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
    public void selectColor() {
        WebElement colorElement = wait.until(ExpectedConditions.presenceOfElementLocated(colorBlack));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", colorElement);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", colorElement);
    }

    @Step("Select shoe size 10")
    public void selectSize() {
        WebElement sizeElement = wait.until(ExpectedConditions.presenceOfElementLocated(size10));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", sizeElement);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", sizeElement);
    }


    public boolean isColorSelected() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(selectedColorBlack)
        ).isDisplayed();
    }
    public boolean isSizeSelected() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(selectedSize10)
        ).isDisplayed();
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