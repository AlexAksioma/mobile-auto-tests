package com.mobile.framework.pages;

import com.mobile.framework.core.BasePage;
import com.mobile.framework.core.View;

/**
 * Page object for the Market screen.
 *
 * <p>Every {@link View} returned here is automatically scoped under the screen's
 * root locator (see the constructor), so callers only need to describe the
 * element itself, not the full path from the page root.</p>
 */
public class MarketPage extends BasePage {

    /**
     * Binds this page to the Market screen's root element on both platforms:
     * {@code market_screen} on Android and {@code MarketViewController} on iOS.
     */
    public MarketPage() {
        super(
            "//*[@resource-id='market_screen']",
            "//*[@name='MarketViewController']"
        );
    }

    /** Search field at the top of the screen used to filter products. */
    public View searchInput1() {
        return view(
            "//*[@resource-id='search_input']",
            "//*[@name='search_textfield']"
        );
    }

    /** Horizontal row of category tabs used to switch product categories. */
    public View categoryTabs() {
        return view(
            "//*[@resource-id='category_tabs']",
            "//*[@name='category_tabs']"
        );
    }

    /** Scrollable list/collection that holds the product cells. */
    public View productList() {
        return view(
            "//*[@resource-id='product_list']",
            "//*[@name='product_collectionview']"
        );
    }

    /** Cart button (usually in the toolbar) that opens the shopping cart. */
    public View cartButton() {
        return view(
            "//*[@resource-id='cart_button']",
            "//*[@name='cart_button']"
        );
    }

    /** Message shown when the current search/category has no products. */
    public View emptyStateMessage() {
        return view(
            "//*[@resource-id='empty_state_text']",
            "//*[@name='empty_state_label']"
        );
    }
}
