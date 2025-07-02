package com.ContactList.UI.pages.SignUpPage.utils;

import com.ContactList.API.core.payloads.UserPayloads.UserBodyPayload;
import com.ContactList.UI.BaseClasses.BaseComponent;
import com.ContactList.UI.utils.controllerUtils.WaitUtils;
import com.ContactList.UI.utils.endpoints.PageEndpoints;
import com.microsoft.playwright.Page;

public class SignUpPageControllers extends BaseComponent {

    public SignUpPageControllers(Page page) {
        super(page);
    }

    private static final String FIRST_NAME = "id=firstName";
    private static final String LAST_NAME = "id=lastName";
    private static final String EMAIL = "id=email";
    private static final String PASSWORD = "id=password";
    private static final String SUBMIT = "id=submit";
    private static final String CANCEL = "id=cancel";

    public void fillUserName(UserBodyPayload name) {
        page.locator(FIRST_NAME).fill(name.getFirstName());
    }

    public void fillUserLastName(UserBodyPayload lastName) {
        page.locator(LAST_NAME).fill(lastName.getLastName());
    }

    public void fillEmail(UserBodyPayload email) {
        page.locator(EMAIL).fill(email.getEmail());
    }

    public void fillPassword(UserBodyPayload password) {
        page.locator(PASSWORD).fill(password.getPassword());
    }

    public void fillUserData(UserBodyPayload payload) {
        this.fillUserName(payload);
        this.fillUserLastName(payload);
        this.fillEmail(payload);
        this.fillPassword(payload);
    }

    public void clickSignUp() {
        page.locator(SUBMIT).click();
        WaitUtils.waitForPageURL(page,PageEndpoints.CONTACT_LIST);
    }

    public void clickOnCancel() {
        page.locator(CANCEL).click();
        WaitUtils.waitForPageURL(page,PageEndpoints.LOGIN);
    }
}
