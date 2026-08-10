package com.mobile.tests;

import com.mobile.framework.components.ProductCard;
import com.mobile.framework.components.ReviewDialog;
import com.mobile.framework.pages.ProductDetailsPage;
import com.mobile.framework.pages.ProductsPage;
import com.mobile.tests.utils.TestNGListener;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

@Listeners(TestNGListener.class)

public class ProductPageTest extends AndroidDriverBaseTest{

    private ProductsPage productsPage;
    private SoftAssert softAssert;

    @BeforeMethod(alwaysRun = true)
    public void initPage(){
        productsPage = new ProductsPage();
        softAssert = new SoftAssert();
    }

    @Test(groups = "smoke")
    public void productsPageAndCatalogAreDisplayed_Test(){
        List<String> visibleProductTitles =
                productsPage.getVisibleProductTitles();

        softAssert.assertTrue(
                productsPage.isDisplayed(),
                "Products page is not displayed"
        );

        softAssert.assertFalse(
                visibleProductTitles.isEmpty(),
                "No products are visible in the catalog"
        );

        softAssert.assertAll();
    }

    @Test(groups = "smoke")
    public void productCardContainsBasicInformation_Test(){
        ProductCard product = productsPage.getFirstProduct();
        String title = product.title().text();
        String price = product.price().text();

        softAssert.assertTrue(product.image().isDisplayed(),
                "Product image is not displayed");

        softAssert.assertFalse(title.isBlank(),
                "Product title is empty");

        softAssert.assertFalse(price.isBlank(),
                "Product price is empty");

        softAssert.assertTrue(product.rating().isDisplayed(),
                "Product rating is not displayed");

        softAssert.assertAll();
    }

    @Test(groups = "smoke")
    public void productDetailsPageOpensWhenClickOnProductImage_Test(){
        ProductCard product = productsPage.getFirstProduct();
        String expectedTitle = product.title().text();

        ProductDetailsPage productDetailsPage = product.openDetails();
        String actualTitle = productDetailsPage.title().text();

        Assert.assertEquals(expectedTitle,
                actualTitle,
                "Product details page title does not match the selected product title");
    }

    @Test(groups = "smoke")
    public void productRatingCanBeSubmitted_Test(){
        ProductCard product = productsPage.getFirstProduct();
        ReviewDialog reviewDialog = product.rateProduct(5);

        Assert.assertEquals(
                reviewDialog.message().text(),
                "Thank you for submitting your review!",
                "Review confirmation message is incorrect"
        );
    }

    @Test(groups = "smoke")
    public void productsCatalogScrolls_Test(){
        List<String> visibleTitlesBeforeScroll =
                productsPage.getVisibleProductTitles();

        productsPage.scrollDown();

        List<String> visibleTitlesAfterScroll =
                productsPage.getVisibleProductTitles();

        softAssert.assertFalse(
                visibleTitlesBeforeScroll.isEmpty(),
                "No product titles are visible before catalog scrolling"
        );

        softAssert.assertFalse(
                visibleTitlesAfterScroll.isEmpty(),
                "No product titles are visible after catalog scrolling"
        );

        softAssert.assertNotEquals(
                visibleTitlesAfterScroll,
                visibleTitlesBeforeScroll,
                "Visible products did not change after catalog scrolling"
        );

        softAssert.assertAll();
    }
}
