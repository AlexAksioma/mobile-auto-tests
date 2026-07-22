package com.mobile.tests;

import com.mobile.framework.core.View;
import com.mobile.framework.pages.ProductsPage;
import com.mobile.tests.utils.TestNGListener;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.List;

@Listeners(TestNGListener.class)

public class ProductPageTest extends AndroidDriverBaseTest{

    private ProductsPage productsPage;

    @BeforeMethod
    public void initPage(){
        productsPage = new ProductsPage();
    }

    @Test
    public void PageIsDisplayedTest(){
        Assert.assertTrue(productsPage.isDisplayed());
    }

    @Test
    public void PageTitleIsCorrectTest(){
        Assert.assertEquals(productsPage.title().text(),"Products");
    }

    @Test
    public void ListOfProductsIsNotEmpty(){
        List<View> productList = productsPage.listOfProducts();
        Assert.assertFalse(productList.isEmpty(), "List of products is empty");
    }
}