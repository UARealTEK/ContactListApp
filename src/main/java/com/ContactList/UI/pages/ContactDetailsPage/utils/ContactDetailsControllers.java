package com.ContactList.UI.pages.ContactDetailsPage.utils;

import com.ContactList.UI.BaseClasses.BaseComponent;
import com.ContactList.UI.pages.ListPage.utils.ContactTableControllers;
import com.ContactList.UI.pages.ListPage.utils.ListPageControllers;
import com.ContactList.UI.utils.customUtils.WaitUtils;
import com.ContactList.UI.utils.endpoints.PageEndpoints;
import com.microsoft.playwright.Dialog;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.impl.junit.PageExtension;
import com.microsoft.playwright.options.LoadState;

public class ContactDetailsControllers extends BaseComponent {

    public ContactDetailsControllers(Page page) {
        super(page);
    }

    private final static String editContact = "id=edit-contact";
    private final static String deleteContact = "id=delete";
    private final static String openContactListPage = "id=return";
    private final static String logout = "id=logout";

    public void clickEditContactButton() {
        page.locator(editContact).click();
    }

    public void clickDeleteContactButton() {
        page.onDialog(Dialog::accept);
        page.locator(deleteContact).click();
        WaitUtils.waitForPageURL(page, PageEndpoints.CONTACT_LIST);
    }

    public void clickOpenContactListPageButton() {
        page.locator(openContactListPage).click();
    }

    public void clickLogoutButton() {
        page.locator(logout).click();
    }
}
