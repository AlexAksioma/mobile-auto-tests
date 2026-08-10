package com.mobile.framework.core;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Map;

/**
 * Provides reusable mobile gestures for page and component views.
 */
public final class Gestures {

    private Gestures() {
    }

    public static void scrollDown(View scrollableView) {
        WebElement element = new WebDriverWait(
                DriverHolder.driver(),
                Duration.ofSeconds(20)
        )
                .ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.visibilityOfElementLocated(
                        scrollableView.by()
                ));

        DriverHolder.driver().executeScript(
                "mobile: scrollGesture",
                Map.of(
                        "elementId", ((RemoteWebElement) element).getId(),
                        "direction", "down",
                        "percent", 0.75
                )
        );
    }

    public static void scrollDownIos(View scrollableView) {
        throw new UnsupportedOperationException(
                "Scroll down gesture is not implemented for iOS"
        );
    }
}
