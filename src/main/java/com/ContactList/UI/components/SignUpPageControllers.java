package com.ContactList.UI.components;

import com.ContactList.API.core.payloads.UserPayloads.UserBodyPayload;
import com.ContactList.UI.utils.endpoints.PageEndpoints;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;

public class SignUpPageControllers extends BaseComponent {

    public SignUpPageControllers(Page page) {
        super(page);
    }

    public void fillUserName(UserBodyPayload name) {
        page.fill("id=firstName", name.getFirstName());
    }

    public void fillUserLastName(UserBodyPayload lastName) {
        page.fill("id=lastName", lastName.getLastName());
    }

    public void fillEmail(UserBodyPayload email) {
        page.fill("id=email", email.getEmail());
    }

    public void fillPassword(UserBodyPayload password) {
        page.fill("id=password", password.getPassword());
    }

    public void fillUserData(UserBodyPayload payload) {
        this.fillUserName(payload);
        this.fillUserLastName(payload);
        this.fillEmail(payload);
        this.fillPassword(payload);
    }

    public void clickSignUp() {
        page.click("id=submit");

        try {
            page.waitForURL(PageEndpoints.getFullContactListURL());
        } catch (PlaywrightException e) {
            throw new AssertionError("The Contacts Page was not Loaded. Expected URL to be -> " + PageEndpoints.getFullContactListURL());
        }
    }

    public void clickOnCancel() {
        page.click("id=cancel");

        try {
            page.waitForURL(PageEndpoints.getFullLoginURL());
        } catch (PlaywrightException e) {
            throw new AssertionError("The Contacts Page was not Loaded. Expected URL to be -> " + PageEndpoints.getFullLoginURL());
        }
    }
}
