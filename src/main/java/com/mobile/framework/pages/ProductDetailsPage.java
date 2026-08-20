package com.mobile.framework.pages;

import com.mobile.framework.components.ReviewDialog;
import com.mobile.framework.core.BasePage;
import com.mobile.framework.core.Locator;
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

    public View ratingStar(int rating) {
        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException(
                    "Rating must be between 1 and 5"
            );
        }

        return view(
                "//*[contains(@resource-id, 'id/start" + rating + "IV')]",
                ""
        );
    }

    public ReviewDialog selectRating(int rating) {
        ratingStar(rating).tap();
        return new ReviewDialog();
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

    public int getQuantity() {
        return Integer.parseInt(quantity().text());
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
     * "aroundIV" only exists next to the currently selected color option.
     */
    public boolean isColorSelected(int index) {
        View colorOption = colorOptions().get(index);

        return colorOption.child(Locator.of(
                "//*[contains(@resource-id, 'id/aroundIV')]",
                ""
        )).exists();
    }

    /**
     * Scrollable container that wraps the product details content.
     */
    @Override
    protected View scrollView() {
        return view(
                "//android.widget.ScrollView",
                ""
        );
    }

}
