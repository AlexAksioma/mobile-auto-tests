package com.mobile.framework.core;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class View {

    private final Locator parentLocator;
    private final Locator ownLocator;
    private final Locator resolvedLocator;

    public View(Locator parentLocator, Locator ownLocator) {
        this.parentLocator = parentLocator;
        this.ownLocator = ownLocator;
        this.resolvedLocator = parentLocator.child(ownLocator);
    }

    public Locator locator() {
        return resolvedLocator;
    }

    public String xpath() {
        return resolvedLocator.xpath();
    }

    public By by() {
        return By.xpath(xpath());
    }


    public View child(Locator childLocator) {
        return new View(this.resolvedLocator, childLocator);
    }



    public void tap() {
        WebElement el = DriverHolder.driver().findElement(by());
        el.click();
    }

    private WebElement waitUntilVisible() {
        return new WebDriverWait(
                DriverHolder.driver(),
                Duration.ofSeconds(20)
        )
                .ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.visibilityOfElementLocated(by()));
    }

    public String text() {
        return waitUntilVisible().getText();
    }

    public void enterText(String text) {
        WebElement el = DriverHolder.driver().findElement(by());
        el.clear();
        el.sendKeys(text);
    }

    public boolean isDisplayed() {
        try {
            return DriverHolder.driver().findElement(by()).isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public boolean exists() {
        return !DriverHolder.driver().findElements(by()).isEmpty();
    }

    @Override
    public String toString() {
        return "View[" + xpath() + "]";
    }
}
