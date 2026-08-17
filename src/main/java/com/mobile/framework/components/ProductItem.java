package com.mobile.framework.components;

import com.mobile.framework.core.Locator;
import com.mobile.framework.core.View;

/**
 * Base UI component for product elements shared between different screens.
 * Limits searches for the image, title, and price to a specific product root.
 */
public abstract class ProductItem {

    protected final View root;

    protected ProductItem(View root) {
        this.root = root;
    }

    public View image() {
        return root.child(Locator.of(
                "//*[contains(@resource-id, 'id/productIV')]",
                ""
        ));
    }

    public View title() {
        return root.child(Locator.of(
                "//*[contains(@resource-id, 'id/titleTV')]",
                ""
        ));
    }

    public View price() {
        return root.child(Locator.of(
                "//*[contains(@resource-id, 'id/priceTV')]",
                ""
        ));
    }

    public View rating() {
        return root.child(Locator.of(
                "//*[contains(@resource-id, 'id/rattingV')]",
                ""
        ));
    }
}
