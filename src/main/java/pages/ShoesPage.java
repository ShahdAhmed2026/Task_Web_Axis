package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
import java.util.*;

public class ShoesPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private By header = By.cssSelector(".page-title.category-title h1");
    private By dorian = By.xpath("//h2[@class='product-name']//a[@title='Dorian Perforated Oxford']");
    private By sortDropdown = By.cssSelector("select[title='Sort By']");
    private By sortDirectionArrow = By.cssSelector(".sort-by-switcher");
    private By prices = By.cssSelector(".products-grid .regular-price .price");


    public ShoesPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    public String getHeader() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(header)).getText();
    }

    @Step("Sort shoes by price ascending low -> high")
    public void sortByPriceAscending() {

        Select select = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(sortDropdown)));
        select.selectByVisibleText("Price");
        wait.until(ExpectedConditions.urlContains("order=price"));  //to make sure sorting is done
        WebElement arrow = wait.until(ExpectedConditions.visibilityOfElementLocated(sortDirectionArrow));
        String arrowClass = arrow.getAttribute("class");
        if (!arrowClass.contains("sort-by-switcher--asc"))
        {
            arrow.click();
            wait.until(ExpectedConditions.attributeContains(sortDirectionArrow, "class", "sort-by-switcher--asc"));
        }
    }

//making sure the prices are sorted in ascending order (Actually)
    public boolean isSortedAscending() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(prices));
        //list of elements
        List<WebElement> priceElements = driver.findElements(prices);
        List<Double> actualPrices = new ArrayList<>();
        for (WebElement priceElement : priceElements) {
            String priceText = priceElement.getText()
                    .replace("$", "")
                    .replace(",", "")
                    .trim();

            if (!priceText.isEmpty()) {
                actualPrices.add(Double.parseDouble(priceText));
            }
        }
        List<Double> expectedPrices = new ArrayList<>(actualPrices);
        Collections.sort(expectedPrices);

        System.out.println("Actual Prices: " + actualPrices);
        System.out.println("Expected Sorted Prices: " + expectedPrices);

        return actualPrices.equals(expectedPrices);
    }



    @Step("Open Dorian Shoes")
    public void openDorian() {
        WebElement product = wait.until(ExpectedConditions.presenceOfElementLocated(dorian));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", product);
        wait.until(ExpectedConditions.urlContains("dorian-preforated-oxford"));}
}