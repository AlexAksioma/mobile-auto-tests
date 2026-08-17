package com.mobile.framework.pages;

import com.mobile.framework.core.BasePage;
import com.mobile.framework.core.Gestures;
import com.mobile.framework.core.View;

import java.util.List;

public class ProductDetailsPage extends BasePage {

    public ProductDetailsPage() {
        super(
                "//*[contains(@resource-id, 'id/fragment_container')]",
                ""
        );
    }

    public View title() {
        return view(
                "//*[contains(@resource-id, 'id/productTV')]",
                ""
        );
    }

    public View price() {
        return view(
                "//*[contains(@resource-id, 'id/priceTV')]",
                ""
        );
    }

    public View rating() {
        return view(
                "//*[contains(@resource-id, 'id/rattingV')]",
                ""
        );
    }

    public List<View> colorOptions() {
        return views(
                "//*[contains(@resource-id, 'id/colorRV')]/*",
                ""
        );
    }

    public View decreaseQuantityButton() {
        return view(
                "//*[contains(@resource-id, 'id/minusIV')]",
                ""
        );
    }

    public View quantity() {
        return view(
                "//*[contains(@resource-id, 'id/noTV')]",
                ""
        );
    }

    public View increaseQuantityButton() {
        return view(
                "//*[contains(@resource-id, 'id/plusIV')]",
                ""
        );
    }

    public View addToCartButton() {
        return view(
                "//*[contains(@resource-id, 'id/cartBt')]",
                ""
        );
    }

    public View productHighlights() {
        return view(
                "//*[contains(@resource-id, 'id/productHeightLightsTV')]",
                ""
        );
    }

    public View productHighlightsText() {
        return view(
                "//*[contains(@resource-id, 'id/descTV')]",
                ""
        );
    }

    public void addToCart() {
        addToCartButton().tap();
    }

    public void increaseQuantity() {
        increaseQuantityButton().tap();
    }

    public void decreaseQuantity() {
        decreaseQuantityButton().tap();
    }

    public void selectColor(int index) {
        colorOptions().get(index).tap();
    }

    /**
     * Scrollable container that wraps the product details content.
     */
    private View scrollView() {
        return view(
                "//android.widget.ScrollView",
                ""
        );
    }

    public void scrollDown() {
        Gestures.scrollDown(scrollView());
    }

}
