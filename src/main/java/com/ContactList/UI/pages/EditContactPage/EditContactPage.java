package com.ContactList.UI.pages.EditContactPage;

import com.ContactList.API.core.payloads.ContactsPayloads.ContactsBodyPayload;
import com.ContactList.UI.BaseClasses.BasePage;
import com.ContactList.UI.pages.ContactDetailsPage.ContactDetailsPage;
import com.ContactList.UI.pages.EditContactPage.utils.ContactEditor;
import com.ContactList.UI.pages.EditContactPage.utils.EditContactControllers;
import com.ContactList.UI.utils.Managers.PageManager;
import io.qameta.allure.Step;

public class EditContactPage extends BasePage {

    EditContactControllers controllers;

    @Override
    public void initComponents() {
        controllers = new EditContactControllers(page);
    }

    @Step("Edit existing contact with predefined data")
    public ContactDetailsPage editContact(ContactsBodyPayload replacementPayload) {
        ContactEditor.editContact(page,replacementPayload);
        controllers.clickSubmitButton();
        return PageManager.createInstance(page, ContactDetailsPage.class);
    }
}
