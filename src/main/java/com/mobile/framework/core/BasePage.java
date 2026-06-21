package com.mobile.framework.core;

public abstract class BasePage {

    private final Locator rootLocator;

    protected BasePage(String androidRootXPath, String iosRootXPath) {
        this.rootLocator = Locator.of(androidRootXPath, iosRootXPath);
    }

    public Locator rootLocator() {
        return rootLocator;
    }

    protected View view(String androidXPath, String iosXPath) {
        return new View(rootLocator, Locator.of(androidXPath, iosXPath));
    }

    protected View view(String xpath) {
        return new View(rootLocator, Locator.same(xpath));
    }

    protected View view(Locator locator) {
        return new View(rootLocator, locator);
    }

    public String rootXPath() {
        return rootLocator.xpath();
    }

    public boolean isDisplayed() {
        try {
            return DriverHolder.driver().findElement(org.openqa.selenium.By.xpath(rootXPath())).isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }
}
