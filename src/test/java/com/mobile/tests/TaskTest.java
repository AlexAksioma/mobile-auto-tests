package com.mobile.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TaskTest {

    @Test
    public void testNewTask() {
        Assert.assertEquals("123", "123");
    }

    @Test
    public void myOwnTest() {
        Assert.assertTrue(5 > 3);
    }

    @Test
    public void testStringContainsText() {
        String text = "mobile automation tests";
        Assert.assertTrue(text.contains("automation"));
    }

    @Test
    public void testNumbersAreEqual() {
        int actual = 10 + 5;
        int expected = 15;
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testBooleanValue() {
        boolean isDisplayed = true;
        Assert.assertTrue(isDisplayed);
    }

    @Test
    public void testStringStartsWithMobile() {
        String text = "mobile automation tests";
        Assert.assertTrue(text.startsWith("mobile"));
    }
}
