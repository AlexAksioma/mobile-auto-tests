package com.mobile.framework.pages;
import com.mobile.framework.components.ProductCard;
import com.mobile.framework.core.BasePage;
import com.mobile.framework.core.DriverHolder;
import com.mobile.framework.core.View;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ProductsPage extends BasePage {

    public ProductsPage() {
        super(
                "//*[contains(@resource-id, 'id/fragment_container')]" +
                        "/android.view.ViewGroup[" +
                        ".//*[contains(@resource-id, 'id/productTV')]" +
                        "]",
                ""
        );
    }

    public View title() {
        return view(
                "//*[contains(@resource-id, 'id/productTV')]",
                ""
        );
    }

    public List<ProductCard> products() {
        List<View> cardRoots = views(
                "//*[contains(@resource-id, 'id/productRV')]/*",
                ""
        );

        List<ProductCard> products = new ArrayList<>();

        for (View cardRoot : cardRoots) {
            ProductCard product = new ProductCard(cardRoot);
            products.add(product);
        }

        return products;
    }

    public List<ProductCard> waitForProducts() {
        WebDriverWait wait = new WebDriverWait(
                DriverHolder.driver(),
                Duration.ofSeconds(20));

        wait.until(driver -> !products().isEmpty());
        return products();
    }
}
