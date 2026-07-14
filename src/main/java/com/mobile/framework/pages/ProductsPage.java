package com.mobile.framework.pages;
import com.mobile.framework.core.BasePage;
import com.mobile.framework.core.View;

import java.util.List;

public class ProductsPage extends BasePage {

    public ProductsPage() {
        super(
                "//*[@resource-id='com.saucelabs.mydemoapp.android:id/fragment_container']" +
                        "/android.view.ViewGroup[" +
                        ".//*[@resource-id='com.saucelabs.mydemoapp.android:id/productTV']" +
                        "]",
                ""
        );
    }

    public View title() {
        return view(
                "//*[@resource-id='com.saucelabs.mydemoapp.android:id/productTV']",
                ""
        );
    }

    public List<View> listOfProducts(){
        return views("//*[@resource-id='com.saucelabs.mydemoapp.android:id/productIV']",
                "");
    }
}
