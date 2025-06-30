package com.ContactList.UI.pages.AddContactPage;

import com.ContactList.API.core.payloads.ContactsPayloads.ContactsBodyPayload;
import com.ContactList.UI.pages.AddContactPage.utils.AddContactPageControllers;
import com.ContactList.UI.BaseClasses.BasePage;
import com.ContactList.UI.pages.ListPage.ListPage;
import com.ContactList.UI.utils.Managers.PageManager;
import io.qameta.allure.Step;

public class AddContactPage extends BasePage {

    private AddContactPageControllers controllers;

    @Override
    public void initComponents() {
        controllers = new AddContactPageControllers(page);
    }

    @Step("Add Contact with a predefined payload")
    public ListPage addContact(ContactsBodyPayload payload) {
        controllers.fillContact(payload);
        controllers.clickSubmit();
        return PageManager.createInstance(page, ListPage.class);
    }

    @Step("Click on 'Cancel' button to return to ListPage")
    public ListPage cancelAddingContact() {
        controllers.clickCancel();
        return PageManager.createInstance(page, ListPage.class);
    }
}
