package com.mobile.framework.core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class View {

    private static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(10);

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
        WebElement el = new WebDriverWait(DriverHolder.driver(), DEFAULT_TIMEOUT)
                .until(ExpectedConditions.elementToBeClickable(by()));
        el.click();
    }

    public String text() {
        WebElement el = new WebDriverWait(DriverHolder.driver(), DEFAULT_TIMEOUT)
                .until(ExpectedConditions.visibilityOfElementLocated(by()));
        return el.getText();
    }

    public void enterText(String text) {
        WebElement el = new WebDriverWait(DriverHolder.driver(), DEFAULT_TIMEOUT)
                .until(ExpectedConditions.visibilityOfElementLocated(by()));
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
