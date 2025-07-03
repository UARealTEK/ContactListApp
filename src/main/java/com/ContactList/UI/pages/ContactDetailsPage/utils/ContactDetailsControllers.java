package com.ContactList.UI.pages.ContactDetailsPage.utils;

import com.ContactList.UI.BaseClasses.BaseComponent;
import com.microsoft.playwright.Page;

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
        page.locator(deleteContact).click();
    }

    public void clickOpenContactListPageButton() {
        page.locator(openContactListPage).click();
    }

    public void clickLogoutButton() {
        page.locator(logout).click();
    }
}
