package com.ContactList.UI.pages.ContactDetailsPage.utils;

import com.ContactList.UI.BaseClasses.BaseComponent;
import com.ContactList.UI.pages.ListPage.utils.ContactTableControllers;
import com.ContactList.UI.utils.customUtils.WaitUtils;
import com.ContactList.UI.utils.customUtils.mappers.Mappers;
import com.ContactList.UI.utils.endpoints.PageEndpoints;
import com.microsoft.playwright.Dialog;
import com.microsoft.playwright.Page;

import lombok.Getter;

import java.util.Map;

import static com.ContactList.UI.pages.EditContactPage.utils.EditContactControllers.editContactForm;

public class ContactDetailsControllers extends BaseComponent {

    public ContactDetailsControllers(Page page) {
        super(page);
    }

    @Getter
    private final static String editContact = "id=edit-contact";
    @Getter
    private final static String deleteContact = "id=delete";
    @Getter
    private final static String openContactListPage = "id=return";
    @Getter
    private final static String logout = "id=logout";

    public void clickEditContactButton() {
        page.locator(editContact).click();
        WaitUtils.waitForAllEditable(page, Mappers.getFORM_FIELD_EDITOR_MAPPINGS().keySet().stream().toList());
    }

    public void clickDeleteContactButton() {
        page.onDialog(Dialog::accept);
        page.locator(deleteContact).click();
        WaitUtils.waitForPageURL(page, PageEndpoints.CONTACT_LIST);
    }

    public void clickOpenContactListPageButton() {
        page.locator(openContactListPage).click();
        WaitUtils.waitForPageURL(page,PageEndpoints.CONTACT_LIST);
        WaitUtils.waitUntilElementIsDisplayed(page, ContactTableControllers.getTable());
    }

    public void clickLogoutButton() {
        page.locator(logout).click();
    }
}
