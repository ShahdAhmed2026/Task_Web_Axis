package tests;

import io.qameta.allure.*;
import io.qameta.allure.testng.AllureTestNg;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.*;

@Feature("Add Product to Cart")
@Listeners({AllureTestNg.class})
public class AddProductToCartTest extends BaseTest {
    @Test
    @Description("Full flow: Login -> Accessories -> Shoes -> Add product to cart")
    public void verifyFullShoppingFlow() throws InterruptedException{
        //login
        driver.get(Login_Url);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("shahd@gmail.com", "1234567");
        //Assert.assertEquals(loginPage.getPageHeader(), "MY DASHBOARD"); --->   //stall, so I will check the URL instead
        Assert.assertTrue(loginPage.isLoginSuccessful());



        // Hover and navigate to shoes
        HomePage homePage = new HomePage(driver);
        homePage.hoverAccessories();
        Assert.assertTrue(homePage.isDropdownVisible());  //lazm at2kd en dropdown mawgoda aslun b3d elhover
        homePage.clickShoes();

        // sort and select product
        ShoesPage shoesPage = new ShoesPage(driver);
        Assert.assertEquals(shoesPage.getHeader(), "SHOES");
        shoesPage.sortByPriceAscending();
        Assert.assertTrue(shoesPage.isSortedAscending());
        shoesPage.openDorian();

        // product details and add to cart
        ProductDetailsPage product = new ProductDetailsPage(driver);
        Assert.assertEquals(
                product.getProductName(),
                "DORIAN PERFORATED OXFORD"
        );
        product.selectColor();
        product.selectSize();
        Assert.assertTrue(product.isColorSelected());
        Assert.assertTrue(product.isSizeSelected());
        product.addToCart();

// verify added to cart message
        CartPage cart = new CartPage(driver);
        Assert.assertEquals(
                cart.getSuccessMessage(),
                "Dorian Perforated Oxford was added to your shopping cart."
        );}

    @Test
    @Description("Verify color is required before adding product to cart")
    public void verifyColorIsRequiredBeforeAddToCart() {
        driver.get(Login_Url);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("shahd@gmail.com", "1234567");

        HomePage homePage = new HomePage(driver);
        homePage.hoverAccessories();
        homePage.clickShoes();

        ShoesPage shoesPage = new ShoesPage(driver);
        shoesPage.openDorian();

        ProductDetailsPage product = new ProductDetailsPage(driver);
        product.selectSize();
        product.clickAddToCartForValidation();
        Assert.assertEquals(product.getColorRequiredMessage(), "This is a required field.");
    }

    @Test
    @Description("Verify Size is required before adding product to cart")
    public void verifySizeIsRequiredBeforeAddToCart() {
        driver.get(Login_Url);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("shahd@gmail.com", "1234567");

        HomePage homePage = new HomePage(driver);
        homePage.hoverAccessories();
        homePage.clickShoes();

        ShoesPage shoesPage = new ShoesPage(driver);
        shoesPage.openDorian();

        ProductDetailsPage product = new ProductDetailsPage(driver);
        product.selectColor();
        product.clickAddToCartForValidation();
        Assert.assertEquals(product.getSizeRequiredMessage(), "This is a required field.");
    }
    }
