package com.mobile.framework.pages;

import com.mobile.framework.core.BasePage;
import com.mobile.framework.core.View;

public class LoginPage extends BasePage {

    public LoginPage() {
        super(
            "//*[@resource-id='login_screen']",
            "//*[@name='LoginViewController']"
        );
    }

    public View emailInput() {
        return view(
            "//*[@resource-id='email_input']",
            "//*[@name='email_textfield']"
        );
    }

    public View passwordInput() {
        return view(
            "//*[@resource-id='password_input']",
            "//*[@name='password_textfield']"
        );
    }

    public View loginButton() {
        return view(
            "//*[@resource-id='login_button']",
            "//*[@name='login_button']"
        );
    }

    public View errorMessage() {
        return view(
            "//*[@resource-id='error_text']",
            "//*[@name='error_label']"
        );
    }

    public View signUpLink() {
        return view(
            "//*[@text='Sign Up']",
            "//*[@label='Sign Up']"
        );
    }
}
