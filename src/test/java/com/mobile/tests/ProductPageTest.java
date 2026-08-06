package com.mobile.tests;

import com.mobile.framework.components.ProductCard;
import com.mobile.framework.pages.ProductsPage;
import com.mobile.tests.utils.TestNGListener;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

@Listeners(TestNGListener.class)

public class ProductPageTest extends AndroidDriverBaseTest{

    private ProductsPage productsPage;
    private SoftAssert softAssert;

    @BeforeMethod
    public void initPage(){
        productsPage = new ProductsPage();
        softAssert = new SoftAssert();
    }

    @Test
    public void ProductsPageAndCatalogAreDisplayedTest(){
        boolean titleDisplayed = productsPage.title().isDisplayed();
        List<ProductCard> products = productsPage.waitForProducts();

        softAssert.assertTrue(productsPage.isDisplayed(),
                "Products page is not displayed");
        if (titleDisplayed)
            softAssert.assertEquals(productsPage.title().text(),"Products",
                    "Products page title is incorrect");
        else
            softAssert.fail("Products page title is not displayed");

        softAssert.assertFalse(products.isEmpty(),
                "Product catalog is empty");

        softAssert.assertAll();
    }

    @Test
    public void ProductCardContainsBasicInformationTest(){
        ProductCard product = productsPage.waitForProducts().get(0);

        boolean imageDisplayed = product.image().isDisplayed();
        boolean titleDisplayed = product.title().isDisplayed();
        boolean priceDisplayed = product.price().isDisplayed();

        softAssert.assertTrue(imageDisplayed,
                "Product image is not displayed");

        if (titleDisplayed)
            softAssert.assertFalse(product.title().text().isBlank(),
                    "Product title is empty");
        else
            softAssert.fail("Product title is not displayed");

        if (priceDisplayed)
            softAssert.assertFalse(product.price().text().isBlank(),
                    "Product price is empty");
        else
            softAssert.fail("Product price is not displayed");

        softAssert.assertAll();
    }
}
