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

    public boolean isDisplayed() {
        try {
            return DriverHolder.driver().findElement(org.openqa.selenium.By.xpath(rootXPath())).isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    protected List<View> views(String androidXpath, String iosXPath) {
        Locator locator = Locator.of(androidXpath, iosXPath);

        int countOfView = DriverHolder.driver()
                .findElements(org.openqa.selenium.By.xpath(rootLocator.child(locator).xpath())).size();

        List <View> listView = new ArrayList<>();
        for (int i = 1; i <= countOfView; i++) {
            listView.add(view("(" + androidXpath + ")[" + i + "]",
                    "(" + iosXPath + ")[" + i + "]"));
        }

        return listView;
    }
}
