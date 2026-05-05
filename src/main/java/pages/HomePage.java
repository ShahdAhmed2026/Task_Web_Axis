package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
public class HomePage {

    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;
    private By accessories = By.xpath("//nav[@id='nav']//a[normalize-space()='Accessories']");
    private By shoes = By.xpath("//nav[@id='nav']//a[normalize-space()='Shoes']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.actions = new Actions(driver);
    }

    @Step("Hover over Accessories")
    public void hoverAccessories() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(accessories));
        actions.moveToElement(element).perform();
    }

    @Step("Check dropdown is visible")      //at2kd en shoes mawgoda aslun b3d elhover
    public boolean isDropdownVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(shoes)).isDisplayed();
    }

    @Step("Click Shoes")
    public void clickShoes() {
        WebElement shoeElement = wait.until(ExpectedConditions.elementToBeClickable(shoes));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", shoeElement);
    }
}