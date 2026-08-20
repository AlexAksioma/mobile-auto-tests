package com.mobile.framework.core;

import java.util.ArrayList;
import java.util.List;

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

    protected View scrollView() {
        throw new UnsupportedOperationException(
                "This page does not support scrolling"
        );
    }

    protected void scroll(ScrollDirection direction, double percent) {
        Gestures.scroll(scrollView(), direction, percent);
    }

    public void scrollDownOneScreen() {
        scroll(ScrollDirection.DOWN, 1.0);
    }

    public void scrollUpOneScreen() {
        scroll(ScrollDirection.UP, 1.0);
    }

    public boolean isDisplayed() {
        try {
            return DriverHolder.driver().findElement(org.openqa.selenium.By.xpath(rootXPath())).isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    protected List<View> views(String androidXPath, String iosXPath) {
        Locator childLocator = Locator.of(androidXPath, iosXPath);
        Locator resolvedLocator = rootLocator.child(childLocator);

        int countOfViews = DriverHolder.driver()
                .findElements(org.openqa.selenium.By.xpath(resolvedLocator.xpath()))
                .size();

        List<View> views = new ArrayList<>();

        for (int i = 1; i <= countOfViews; i++) {
            Locator indexedLocator = Locator.of(
                    "(" + resolvedLocator.androidXPath() + ")[" + i + "]",
                    "(" + resolvedLocator.iosXPath() + ")[" + i + "]"
            );

            views.add(new View(Locator.same(""), indexedLocator));
        }

        return views;
    }
}
