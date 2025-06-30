package com.ContactList.UI.pages.LoginPage.utils;

import com.ContactList.API.core.payloads.UserPayloads.UserBodyPayload;
import com.ContactList.UI.BaseClasses.BaseComponent;
import com.ContactList.UI.utils.endpoints.PageEndpoints;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;

public class LoginPageControllers extends BaseComponent {

    public LoginPageControllers(Page page) {
        super(page);
    }

    /**
     * Inner locator class for local usage
     */

    private static final class Locators {
        private static final String EMAIL = "id=email";
        private static final String PASSWORD = "id=password";
        private static final String SUBMIT = "#submit";
        private static final String SIGNUP = "id=signup";
    }

    public void fillUserName(UserBodyPayload payload) {
        page.locator(Locators.EMAIL).fill(payload.getEmail());
    }

    public void fillPassword(UserBodyPayload payload) {
        page.locator(Locators.PASSWORD).fill(payload.getPassword());
    }

    public void clickOnLogin() {
        page.locator(Locators.SUBMIT).click();

        try {
            page.waitForURL(PageEndpoints.getFullContactListURL());
        } catch (PlaywrightException e) {
            throw new AssertionError("The page URL has not changed after the click. " +
                    "expected URL to be -> " + PageEndpoints.getFullContactListURL(), e);
        }
    }

    public void clickOnSignUp() {
        page.locator(Locators.SIGNUP).click();

        try {
            page.waitForURL(PageEndpoints.getFullSignUpURL());
        } catch (PlaywrightException e) {
            throw new AssertionError("The page URL has not changed after the click. " +
                    "expected URL to be -> " + PageEndpoints.getFullSignUpURL(), e);
        }
    }

}
