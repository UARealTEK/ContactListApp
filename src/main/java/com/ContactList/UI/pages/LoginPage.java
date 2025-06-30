package com.ContactList.UI.pages;

import com.ContactList.API.core.payloads.UserPayloads.UserBodyPayload;
import com.ContactList.UI.components.LoginPageControllers;
import com.ContactList.UI.utils.factories.BasePageFactory;
import io.qameta.allure.Step;

import static com.ContactList.UI.config.ConfigurationManager.config;

public class LoginPage extends BasePage {

    private LoginPageControllers controllers;

    @Override
    public void initComponents() {
        controllers = new LoginPageControllers(page);
    }

    @Step("Open login page")
    public LoginPage openLoginPage() {
        page.navigate(config().baseURL());
        return this;
    }

    @Step("Open SignUpPage")
    public SignUpPage openSignUpPage() {
        this.openLoginPage();
        controllers.clickOnSignUp();
        return BasePageFactory.createInstance(page, SignUpPage.class);
    }

    @Step("Log in as a user with specified Credentials")
    public ListPage loginAsUser(UserBodyPayload payload) {
        this.openLoginPage();
        controllers.fillUserName(payload);
        controllers.fillPassword(payload);
        controllers.clickOnLogin();
        return BasePageFactory.createInstance(page,ListPage.class);
    }
}
