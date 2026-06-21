package com.mobile.framework.pages;

import com.mobile.framework.core.BasePage;
import com.mobile.framework.core.Locator;
import com.mobile.framework.core.View;

public class HomePage extends BasePage {

    public HomePage() {
        super(
            "//*[@resource-id='home_screen']",
            "//*[@name='HomeViewController']"
        );
    }

    public View welcomeLabel() {
        return view(
            "//*[@resource-id='welcome_text']",
            "//*[@name='welcome_label']"
        );
    }

    public View settingsButton() {
        return view(
            "//*[@resource-id='settings_btn']",
            "//*[@name='settings_button']"
        );
    }

    public View profileSection() {
        return view(
            "//*[@resource-id='profile_section']",
            "//*[@name='profile_view']"
        );
    }

    public View profileAvatar() {
        return profileSection().child(Locator.of(
            "//*[@resource-id='avatar_image']",
            "//*[@name='avatar_imageview']"
        ));
    }

    public View logoutButton() {
        return view(
            "//*[@resource-id='logout_btn']",
            "//*[@name='logout_button']"
        );
    }
}
