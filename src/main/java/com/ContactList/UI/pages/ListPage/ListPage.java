package com.ContactList.UI.pages.ListPage;

import com.ContactList.UI.pages.ContactDetailsPage;
import com.ContactList.UI.pages.ListPage.utils.ListPageControllers;
import com.ContactList.UI.pages.AddContactPage.AddContactPage;
import com.ContactList.UI.BaseClasses.BasePage;
import com.ContactList.UI.utils.Managers.PageManager;
import io.qameta.allure.Step;
import lombok.Getter;

public class ListPage extends BasePage {

    @Getter
    private ListPageControllers controllers;

    @Override
    public void initComponents() {
        controllers = new ListPageControllers(page);
    }

    @Step("Clicking on Add Contact button to proceed with adding a new Contact")
    public AddContactPage openAddContactPage() {
        controllers.clickAddContactButton();
        return PageManager.createInstance(page, AddContactPage.class);
    }

    @Step("click on the latest created contact")
    public ContactDetailsPage openContactDetailsPage() {
        controllers.getTableController().waitUntilTableIsDisplayed();
        controllers.getTableController().clickRandomRow();
        return PageManager.createInstance(page, ContactDetailsPage.class);
    }

}
