package com.mobile.tests;

import com.mobile.framework.components.ProductCard;
import com.mobile.framework.pages.ProductDetailsPage;
import com.mobile.framework.pages.ProductsPage;
import com.mobile.tests.utils.TestNGListener;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Listeners(TestNGListener.class)
public class ProductDetailsTest extends AndroidDriverBaseTest {

    private ProductsPage productsPage;
    private SoftAssert softAssert;

    @BeforeMethod(alwaysRun = true)
    public void initPage(){
        productsPage = new ProductsPage();
        softAssert = new SoftAssert();
    }

    @Test(groups = "smoke")
    public void productDetailsPageContainsAllElements_Test(){
        ProductCard product = productsPage.getFirstProduct();
        ProductDetailsPage productDetailsPage = product.openDetails();

        softAssert.assertTrue(productDetailsPage.title().isDisplayed(),
                "Product title is not displayed");

        productDetailsPage.scrollDown();

        softAssert.assertTrue(productDetailsPage.price().isDisplayed(),
                "Product price is not displayed");

        softAssert.assertTrue(productDetailsPage.rating().isDisplayed(),
                "Product rating is not displayed");

        softAssert.assertFalse(productDetailsPage.colorOptions().isEmpty(),
                "No color options are displayed");

        softAssert.assertTrue(productDetailsPage.decreaseQuantityButton().isDisplayed(),
                "Decrease quantity button is not displayed");

        softAssert.assertTrue(productDetailsPage.quantity().isDisplayed(),
                "Quantity is not displayed");

        softAssert.assertTrue(productDetailsPage.increaseQuantityButton().isDisplayed(),
                "Increase quantity button is not displayed");

        softAssert.assertTrue(productDetailsPage.addToCartButton().isDisplayed(),
                "Add to cart button is not displayed");

        softAssert.assertTrue(productDetailsPage.productHighlights().isDisplayed(),
                "Product highlights title is not displayed");

        softAssert.assertTrue(productDetailsPage.productHighlightsText().isDisplayed(),
                "Product highlights text is not displayed");

        softAssert.assertAll();
    }


}
