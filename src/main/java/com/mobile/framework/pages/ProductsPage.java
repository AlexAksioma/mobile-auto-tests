package com.mobile.framework.pages;

import com.mobile.framework.core.BasePage;
import com.mobile.framework.core.View;

import java.util.List;

public class ProductsPage extends BasePage {

    public ProductsPage() {
        super("//android.view.ViewGroup[//*[@resource-id='com.saucelabs.mydemoapp.android:id/productTV']]",
                "");
    }

    public List<View> listOfProducts(){
        return views("//*[@resource-id='com.saucelabs.mydemoapp.android:id/productIV']",
                "");
    }

}
