package com.ContactList.UI.pages.LoginPage;

import com.ContactList.API.core.payloads.UserPayloads.UserBodyPayload;
import com.ContactList.UI.pages.LoginPage.utils.LoginPageControllers;
import com.ContactList.UI.BaseClasses.BasePage;
import com.ContactList.UI.pages.ListPage.ListPage;
import com.ContactList.UI.pages.SignUpPage.SignUpPage;
import com.ContactList.UI.utils.Managers.PageManager;
import io.qameta.allure.Step;

import static com.ContactList.UI.utils.Managers.ConfigurationManager.config;

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
        return PageManager.createInstance(page, SignUpPage.class);
    }

    @Step("Log in as a user with specified Credentials")
    public ListPage loginAsUser(UserBodyPayload payload) {
        this.openLoginPage();
        controllers.fillUserName(payload);
        controllers.fillPassword(payload);
        controllers.clickOnLogin();
        return PageManager.createInstance(page,ListPage.class);
    }
}
