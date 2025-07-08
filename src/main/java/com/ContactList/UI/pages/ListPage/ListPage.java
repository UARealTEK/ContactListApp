package com.ContactList.UI.pages.ListPage;

import com.ContactList.UI.pages.ListPage.utils.ListPageControllers;
import com.ContactList.UI.pages.AddContactPage.AddContactPage;
import com.ContactList.UI.BaseClasses.BasePage;
import com.ContactList.UI.utils.Managers.PageManager;
import io.qameta.allure.Step;

public class ListPage extends BasePage {

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

}
