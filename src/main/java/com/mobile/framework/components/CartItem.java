package com.mobile.framework.components;

import com.mobile.framework.core.Locator;
import com.mobile.framework.core.View;

/**
 * Represents a single product entry in the shopping cart.
 * Provides access to its quantity controls and removal action.
 */
public final class CartItem extends ProductItem {

    public CartItem(View root) {
        super(root);
    }

    public View quantity() {
        return root.child(Locator.of(
                "",
                ""
        ));
    }

    public View increaseQuantityButton() {
        return root.child(Locator.of(
                "",
                ""
        ));
    }

    public View decreaseQuantityButton() {
        return root.child(Locator.of(
                "",
                ""
        ));
    }

    public View removeButton() {
        return root.child(Locator.of(
                "",
                ""
        ));
    }

    public void increaseQuantity() {
        increaseQuantityButton().tap();
    }

    public void decreaseQuantity() {
        decreaseQuantityButton().tap();
    }

    public void remove() {
        removeButton().tap();
    }
}
