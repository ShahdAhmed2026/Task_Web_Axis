package tests;

import io.qameta.allure.*;
import io.qameta.allure.testng.AllureTestNg;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.ConfigReader;
import pages.*;

@Feature("Add Product to Cart")
@Listeners({AllureTestNg.class})
public class AddProductToCartTest extends BaseTest {
    //helper method to open Dorian product details page avoid code duplication between tests
    private ProductDetailsPage openDorianProduct() {
        driver.get(Login_Url);
        //login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(
                ConfigReader.get("validEmail"),
                ConfigReader.get("validPassword")
        );

        Assert.assertTrue(loginPage.isLoginSuccessful());

        //hover
        HomePage homePage = new HomePage(driver);
        homePage.clickShoes();

        //sort and navigate to product
        ShoesPage shoesPage = new ShoesPage(driver);
        Assert.assertEquals(shoesPage.getHeader(), "SHOES");
        shoesPage.sortByPriceAscending();
        Assert.assertTrue(shoesPage.isSortedAscending());
        shoesPage.openDorian();

        ProductDetailsPage product = new ProductDetailsPage(driver);
        Assert.assertEquals(product.getProductName(), "DORIAN PERFORATED OXFORD");
        return product;
    }

    @Test
    @Description("Full flow: Login -> Accessories -> Shoes -> Add product to cart")
    public void verifyFullShoppingFlow() {
        ProductDetailsPage product = openDorianProduct();

        product.selectColor();
        product.selectSize();

        Assert.assertTrue(product.isColorSelected());
        Assert.assertTrue(product.isSizeSelected());

        product.addToCart();

        CartPage cart = new CartPage(driver);
        Assert.assertEquals(cart.getSuccessMessage(), "Dorian Perforated Oxford was added to your shopping cart.");
    }

    @Test
    @Description("Verify color is required before adding product to cart")
    public void verifyColorIsRequiredBeforeAddToCart() {
        ProductDetailsPage product = openDorianProduct();

        product.selectSize();
        product.clickAddToCartForValidation();

        Assert.assertEquals(product.getColorRequiredMessage(), "This is a required field.");
    }

    @Test
    @Description("Verify size is required before adding product to cart")
    public void verifySizeIsRequiredBeforeAddToCart() {
        ProductDetailsPage product = openDorianProduct();

        product.selectColor();
        product.clickAddToCartForValidation();

        Assert.assertEquals(product.getSizeRequiredMessage(), "This is a required field.");
    }
}