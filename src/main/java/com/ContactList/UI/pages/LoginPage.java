package com.ContactList.UI.pages;

import io.qameta.allure.Step;

import static com.ContactList.UI.config.ConfigurationManager.config;

public class LoginPage extends BasePage {

    @Override
    public void initComponents() {}

    @Step("Open login page")
    public LoginPage openLoginPage() {
        page.navigate(config().baseURL());
        return this;
    }
}
