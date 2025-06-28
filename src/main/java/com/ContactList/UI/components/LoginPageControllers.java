package com.ContactList.UI.components;

import com.ContactList.API.core.payloads.UserPayloads.UserBodyPayload;
import com.ContactList.UI.utils.endpoints.PageEndpoints;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;

public class LoginPageControllers extends BaseComponent {

    public LoginPageControllers(Page page) {
        super(page);
    }

    public void fillUserName(UserBodyPayload payload) {
        page.fill("id=email", payload.getEmail());
    }

    public void fillPassword(UserBodyPayload payload) {
        page.fill("id=password", payload.getPassword());
    }

    public void clickOnLogin() {
        page.locator("#submit").click();

        try {
            page.waitForURL(PageEndpoints.getFullContactListURL());
        } catch (PlaywrightException e) {
            throw new AssertionError("The page URL has not changed after the click. " +
                    "expected URL to be -> " + PageEndpoints.getFullContactListURL(), e);
        }
    }

    public void clickOnSignUp() {
        page.click("id=signup");

        try {
            page.waitForURL(PageEndpoints.getFullSignUpURL());
        } catch (PlaywrightException e) {
            throw new AssertionError("The page URL has not changed after the click. " +
                    "expected URL to be -> " + PageEndpoints.getFullSignUpURL(), e);
        }
    }

}
