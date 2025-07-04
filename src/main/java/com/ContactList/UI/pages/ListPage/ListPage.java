package com.ContactList.UI.pages.ListPage;

import com.ContactList.UI.pages.ContactDetailsPage.ContactDetailsPage;
import com.ContactList.UI.pages.ListPage.utils.ContactTableControllers;
import com.ContactList.UI.pages.ListPage.utils.ListPageControllers;
import com.ContactList.UI.pages.AddContactPage.AddContactPage;
import com.ContactList.UI.BaseClasses.BasePage;
import com.ContactList.UI.utils.Managers.PageManager;
import com.ContactList.UI.utils.customUtils.WaitUtils;
import io.qameta.allure.Step;
import lombok.Getter;
/**
 * NOTE: Verification of equality between submitted Contact info and the actual data in the table
 * is dubious. I'm not sure how to properly retrieve the data from the Table
 * since it accepts multiple word input for some fields
 */
public class ListPage extends BasePage {

    @Getter
    private ListPageControllers controllers;
    @Getter
    private ContactTableControllers table;

    @Override
    public void initComponents() {
        controllers = new ListPageControllers(page);
        table = new ContactTableControllers(page);
    }

    @Step("Clicking on Add Contact button to proceed with adding a new Contact")
    public AddContactPage openAddContactPage() {
        controllers.clickAddContactButton();
        return PageManager.createInstance(page, AddContactPage.class);
    }

    @Step("click on the random created contact")
    public ContactDetailsPage openContactDetailsPage() {
        WaitUtils.waitUntilElementIsDisplayed(page,ContactTableControllers.getTable());
        table.clickRandomRow();
        return PageManager.createInstance(page, ContactDetailsPage.class);
    }

}
