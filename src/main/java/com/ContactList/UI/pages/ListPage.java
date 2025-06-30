package com.ContactList.UI.pages;

import com.ContactList.UI.components.ListPageControllers;
import com.ContactList.UI.utils.factories.BasePageFactory;
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
        return BasePageFactory.createInstance(page, AddContactPage.class);
    }

}
