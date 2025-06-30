package com.ContactList.UI.pages.ListPage.utils;

import com.ContactList.UI.BaseClasses.BaseComponent;
import com.ContactList.UI.utils.endpoints.PageEndpoints;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;

public class ListPageControllers extends BaseComponent {

    public ListPageControllers(Page page) {
        super(page);
    }

    /**
     * Inner locator class for local usage
     */

    private static final class Locators {
        private static final String ADD_CONTACT = "id=add-contact";
        private static final String LOGOUT = "id=logout";
    }

    public void clickAddContactButton() {
        page.locator(Locators.ADD_CONTACT).click();

        try {
            page.waitForURL(PageEndpoints.getFullAddContactURL());
        } catch (PlaywrightException e) {
            throw new AssertionError("The Add Contact page was not opened." +
                    " Expected URL to be -> " + PageEndpoints.getFullAddContactURL());
        }
    }

    public void clickLogoutButton() {
        page.locator(Locators.LOGOUT).click();

        try {
            page.waitForURL(PageEndpoints.getFullLoginURL());
        } catch (PlaywrightException e) {
            throw new AssertionError("The Login page was not opened." +
                    " Expected URL to be -> " + PageEndpoints.getFullLoginURL());
        }
    }
}
