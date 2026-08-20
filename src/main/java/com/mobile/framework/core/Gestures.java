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

    public static void scroll(View scrollableView, ScrollDirection direction, double percent) {
        if (percent <= 0 || percent > 1) {
            throw new IllegalArgumentException(
                    "percent must be between 0 (exclusive) and 1 (inclusive), was " + percent
            );
        }

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
                        "direction", direction.value(),
                        "percent", percent
                )
        );
    }

    public static void scrollIos(View scrollableView, ScrollDirection direction, double percent) {
        throw new UnsupportedOperationException(
                "Scroll gesture is not implemented for iOS"
        );
    }
}
