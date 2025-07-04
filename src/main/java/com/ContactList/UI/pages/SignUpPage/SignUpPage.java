package com.ContactList.UI.pages.SignUpPage;

import com.ContactList.API.core.payloads.UserPayloads.UserBodyPayload;
import com.ContactList.UI.pages.SignUpPage.utils.SignUpPageControllers;
import com.ContactList.UI.BaseClasses.BasePage;
import com.ContactList.UI.pages.ListPage.ListPage;
import com.ContactList.UI.pages.LoginPage.LoginPage;
import com.ContactList.UI.utils.Managers.PageManager;
import io.qameta.allure.Step;

public class SignUpPage extends BasePage {

    private SignUpPageControllers controllers;

    @Override
    public void initComponents(){
        controllers = new SignUpPageControllers(page);
    }

    @Step("SignUp user using predefined user Data")
    public ListPage signUpUser(UserBodyPayload payload) {
        controllers.fillUserData(payload);
        controllers.clickSignUp();
        return PageManager.createInstance(page, ListPage.class);
    }

    @Step("Cancel signUp process by clicking on the 'Cancel' button")
    public LoginPage cancelSignUp() {
        controllers.clickOnCancel();
        return PageManager.createInstance(page, LoginPage.class);
    }
}
