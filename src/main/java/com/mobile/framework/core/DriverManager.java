package com.mobile.framework.core;

import com.mobile.framework.config.MobileConfig;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;

import java.net.MalformedURLException;
import java.net.URI;

/**
 * Manages the lifecycle of the Appium driver for the selected mobile platform.
 *
 * Creates and configures an AndroidDriver or IOSDriver
 * according to current platform, stores the active driver in
 * DriverHolder, and closes the session after test execution.
 */
public final class DriverManager {

    private DriverManager() {}

    public static void startDriver() throws MalformedURLException {
        AppiumDriver driver = switch (Platform.current()) {
            case ANDROID -> createAndroidDriver();
            case IOS -> createIosDriver();
        };

        DriverHolder.set(driver);
    }

    private static AndroidDriver createAndroidDriver() throws MalformedURLException {

        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName("Android")
                .setAutomationName("UiAutomator2")
                .setDeviceName(MobileConfig.ANDROID_DEVICE_NAME)
                .setAppPackage(MobileConfig.ANDROID_APP_PACKAGE)
                .setAppActivity(MobileConfig.ANDROID_APP_ACTIVITY);

        return new AndroidDriver(
                URI.create(MobileConfig.APPIUM_URL).toURL(),
                options
        );
    }

    private static IOSDriver createIosDriver() throws MalformedURLException {
        XCUITestOptions options = new XCUITestOptions()
                .setPlatformName("iOS")
                .setAutomationName("XCUITest")
                .setDeviceName(MobileConfig.IOS_DEVICE_NAME)
                .setApp(MobileConfig.IOS_APP_PATH)
                .setBundleId(MobileConfig.IOS_BUNDLE_ID);

        return new IOSDriver(
                URI.create(MobileConfig.APPIUM_URL).toURL(),
                options
        );
    }

    public static void quitDriver() {
        DriverHolder.clear();
    }
}
