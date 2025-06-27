package com.ContactList.UI.components;

import com.ContactList.API.core.payloads.UserPayloads.UserBodyPayload;
import com.ContactList.API.core.responses.userResponses.UserResponse;
import com.microsoft.playwright.Page;

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
    }

    public void clickOnSignUp() {
        page.click("id=signup");
    }

}
