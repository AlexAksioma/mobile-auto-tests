package com.mobile.framework.pages;

import com.mobile.framework.components.CartItem;
import com.mobile.framework.core.BasePage;
import com.mobile.framework.core.View;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePage {
    public CartPage() {
        super("//*[contains(@resource-id, 'id/fragment_container')]",
                "");
    }

    public View cartContent() {
        return view("//*[contains(@resource-id, 'id/cartCL')]",
                "");
    }

    public View noItemsMessage() {
        return view("//*[contains(@resource-id, 'id/noItemTitleTV')]",
                "");
    }

    private List<CartItem> cartItems() {
        List<View> itemRoots = views(
                "//*[contains(@resource-id, 'id/productRV')]/*",
                ""
        );

        List<CartItem> cartItems = new ArrayList<>();

        for (View itemRoot : itemRoots) {
            CartItem cartItem = new CartItem(itemRoot);
            cartItems.add(cartItem);
        }

        return cartItems;
    }

    public List<CartItem> getCartItems() {
        return cartItems();
    }

    public View shoppingBtn() {
        return view("//*[contains(@resource-id, 'id/shoppingBt')]",
                "");
    }

    public View title(){
        return view("//*[contains(@resource-id, 'id/productTV')]",
                "");
    }

    public View chekoutBtn(){
        return view("//*[contains(@resource-id, 'id/cartBt')]",
                "");
    }

    public View totalPrice(){
        return view("//*[contains(@resource-id, 'id/totalPriceTV')]",
                "");
    }

    public View totalItems(){
        return view("//*[contains(@resource-id, 'id/itemsTV')]",
                "");
    }

    public void checkout(){
        chekoutBtn().tap();
    }

}
