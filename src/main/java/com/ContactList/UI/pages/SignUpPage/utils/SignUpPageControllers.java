package com.ContactList.UI.pages.SignUpPage.utils;

import com.ContactList.API.core.payloads.UserPayloads.UserBodyPayload;
import com.ContactList.UI.BaseClasses.BaseComponent;
import com.ContactList.UI.utils.endpoints.PageEndpoints;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;

public class SignUpPageControllers extends BaseComponent {

    public SignUpPageControllers(Page page) {
        super(page);
    }

    /**
     * Inner locator class for local usage
     */

    private static final class Locators {
        private static final String FIRST_NAME = "id=firstName";
        private static final String LAST_NAME = "id=lastName";
        private static final String EMAIL = "id=email";
        private static final String PASSWORD = "id=password";
        private static final String SUBMIT = "id=submit";
        private static final String CANCEL = "id=cancel";
    }

    public void fillUserName(UserBodyPayload name) {
        page.locator(Locators.FIRST_NAME).fill(name.getFirstName());
    }

    public void fillUserLastName(UserBodyPayload lastName) {
        page.locator(Locators.LAST_NAME).fill(lastName.getLastName());
    }

    public void fillEmail(UserBodyPayload email) {
        page.locator(Locators.EMAIL).fill(email.getEmail());
    }

    public void fillPassword(UserBodyPayload password) {
        page.locator(Locators.PASSWORD).fill(password.getPassword());
    }

    public void fillUserData(UserBodyPayload payload) {
        this.fillUserName(payload);
        this.fillUserLastName(payload);
        this.fillEmail(payload);
        this.fillPassword(payload);
    }

    public void clickSignUp() {
        page.locator(Locators.SUBMIT).click();

        try {
            page.waitForURL(PageEndpoints.getFullContactListURL());
        } catch (PlaywrightException e) {
            throw new AssertionError("The List Page was not Loaded. Expected URL to be -> " + PageEndpoints.getFullContactListURL());
        }
    }

    public void clickOnCancel() {
        page.locator(Locators.CANCEL).click();

        try {
            page.waitForURL(PageEndpoints.getFullLoginURL());
        } catch (PlaywrightException e) {
            throw new AssertionError("The Contacts Page was not Loaded. Expected URL to be -> " + PageEndpoints.getFullLoginURL());
        }
    }
}
