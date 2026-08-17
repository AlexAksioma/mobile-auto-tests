package com.mobile.framework.components;

import com.mobile.framework.core.Locator;
import com.mobile.framework.core.View;
import com.mobile.framework.pages.ProductDetailsPage;

/**
 * Represents a product card in the catalog.
 * Provides access to its rating and opens the product by tapping its image.
 */
public final class ProductCard extends ProductItem {

    public ProductCard(View root) {
        super(root);
    }

    private View ratingStar(int rating) {
        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException(
                    "Rating must be between 1 and 5"
            );
        }

        return root.child(Locator.of(
                "//*[contains(@resource-id, 'id/start" + rating + "IV')]",
                ""
        ));
    }


    public void tap() {
        image().tap();
    }

    public ProductDetailsPage openDetails() {
        tap();
        return new ProductDetailsPage();
    }

    public ReviewDialog rateProduct(int rating) {
        ratingStar(rating).tap();
        return new ReviewDialog();
    }
}
    
