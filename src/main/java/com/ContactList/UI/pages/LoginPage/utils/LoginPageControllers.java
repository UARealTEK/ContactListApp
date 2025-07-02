package com.ContactList.UI.pages.LoginPage.utils;

import com.ContactList.API.core.payloads.UserPayloads.UserBodyPayload;
import com.ContactList.UI.BaseClasses.BaseComponent;
import com.ContactList.UI.utils.controllerUtils.WaitUtils;
import com.ContactList.UI.utils.endpoints.PageEndpoints;
import com.microsoft.playwright.Page;

public class LoginPageControllers extends BaseComponent {

    public LoginPageControllers(Page page) {
        super(page);
    }

    private static final String EMAIL = "id=email";
    private static final String PASSWORD = "id=password";
    private static final String SUBMIT = "#submit";
    private static final String SIGNUP = "id=signup";


    public void fillUserName(UserBodyPayload payload) {
        page.locator(EMAIL).fill(payload.getEmail());
    }

    public void fillPassword(UserBodyPayload payload) {
        page.locator(PASSWORD).fill(payload.getPassword());
    }

    public void clickOnLogin() {
        page.locator(SUBMIT).click();
        WaitUtils.waitForPageURL(page,PageEndpoints.CONTACT_LIST);
    }

    public void clickOnSignUp() {
        page.locator(SIGNUP).click();
        WaitUtils.waitForPageURL(page,PageEndpoints.SIGN_UP);
    }

}
