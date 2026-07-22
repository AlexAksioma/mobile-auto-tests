package com.mobile.framework.core;

import io.appium.java_client.AppiumDriver;

public final class DriverHolder {

    private static final ThreadLocal<AppiumDriver> DRIVER = new ThreadLocal<>();

    private DriverHolder() {}

    public static void set(AppiumDriver driver) {
        DRIVER.set(driver);
    }

    public static AppiumDriver driver() {
        AppiumDriver d = DRIVER.get();
        if (d == null) {
            throw new IllegalStateException("AppiumDriver not initialized. Call DriverHolder.set() first.");
        }
        return d;
    }

    public static void clear() {
        AppiumDriver d = DRIVER.get();
        if (d != null) {
            d.quit();
        }
        DRIVER.remove();
    }
}