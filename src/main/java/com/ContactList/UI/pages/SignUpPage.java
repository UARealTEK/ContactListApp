package com.ContactList.UI.pages;

import com.ContactList.API.core.payloads.UserPayloads.UserBodyPayload;
import com.ContactList.UI.components.SignUpPageControllers;
import com.ContactList.UI.utils.factories.BasePageFactory;

public class SignUpPage extends BasePage {

    private SignUpPageControllers controllers;

    @Override
    public void initComponents(){
        controllers = new SignUpPageControllers(page);
    }

    public ListPage signUpUser(UserBodyPayload payload) {
        controllers.fillUserData(payload);
        controllers.clickSignUp();

        return BasePageFactory.createInstance(page, ListPage.class);
    }

    public LoginPage cancelSignUp() {
        controllers.clickOnCancel();

        return BasePageFactory.createInstance(page, LoginPage.class);
    }
}
