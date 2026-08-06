package com.mobile.framework.components;

import com.mobile.framework.core.Locator;
import com.mobile.framework.core.View;

public final class ProductCard {

    private final View root;

    public ProductCard(View root) {
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

    public void tap() {
        image().tap();
    }
}
    
