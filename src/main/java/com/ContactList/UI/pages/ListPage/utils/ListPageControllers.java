package com.ContactList.UI.pages.ListPage.utils;

import com.ContactList.UI.BaseClasses.BaseComponent;
import com.ContactList.UI.utils.controllerUtils.WaitUtils;
import com.ContactList.UI.utils.endpoints.PageEndpoints;
import com.microsoft.playwright.Page;

public class ListPageControllers extends BaseComponent {

    public ListPageControllers(Page page) {
        super(page);
    }

    private static final String ADD_CONTACT = "id=add-contact";
    private static final String LOGOUT = "id=logout";

    public void clickAddContactButton() {
        page.locator(ADD_CONTACT).click();
        WaitUtils.waitForPageURL(page,PageEndpoints.ADD_CONTACT);
    }

    public void clickLogoutButton() {
        page.locator(LOGOUT).click();
        WaitUtils.waitForPageURL(page,PageEndpoints.LOGIN);
    }
}
