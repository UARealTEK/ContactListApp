package com.ContactList.UI.components;

import com.ContactList.UI.utils.endpoints.PageEndpoints;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;

public class ListPageControllers extends BaseComponent {

    public ListPageControllers(Page page) {
        super(page);
    }

    public void clickAddContactButton() {
        page.click("id=add-contact");

        try {
            page.waitForURL(PageEndpoints.getFullAddContactURL());
        } catch (PlaywrightException e) {
            throw new AssertionError("The Add Contact page was not opened." +
                    " Expected URL to be -> " + PageEndpoints.getFullAddContactURL());
        }
    }

    public void clickLogoutButton() {
        page.click("id=logout");

        try {
            page.waitForURL(PageEndpoints.getFullLoginURL());
        } catch (PlaywrightException e) {
            throw new AssertionError("The Login page was not opened." +
                    " Expected URL to be -> " + PageEndpoints.getFullLoginURL());
        }
    }
}
