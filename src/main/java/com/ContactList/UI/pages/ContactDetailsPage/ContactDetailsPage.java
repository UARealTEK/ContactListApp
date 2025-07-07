package com.ContactList.UI.pages.ContactDetailsPage;

import com.ContactList.UI.BaseClasses.BasePage;
import com.ContactList.UI.pages.ContactDetailsPage.utils.ContactDetailsControllers;
import com.ContactList.UI.pages.ContactDetailsPage.utils.ContactDetailsFormControllers;
import com.ContactList.UI.pages.EditContactPage.EditContactPage;
import com.ContactList.UI.pages.ListPage.ListPage;
import com.ContactList.UI.pages.LoginPage.LoginPage;
import com.ContactList.UI.utils.Managers.PageManager;
import io.qameta.allure.Step;
import lombok.Getter;

public class ContactDetailsPage extends BasePage {

    @Getter
    ContactDetailsControllers controllers;
    @Getter
    ContactDetailsFormControllers form;

    @Override
    public void initComponents() {
        controllers = new ContactDetailsControllers(page);
        form = new ContactDetailsFormControllers(page);
    }

    @Step("Editing contact by clicking on the edit contact button")
    public EditContactPage openEditContact() {
        controllers.clickEditContactButton();
        return PageManager.createInstance(page, EditContactPage.class);
    }

    @Step("delete user by clicking on the 'delete' button and confirming it by clicking on the appeared prompt")
    public ListPage deleteContact() {
        controllers.clickDeleteContactButton();
        return PageManager.createInstance(page, ListPage.class);
    }

    @Step
    public ListPage openContactListPage() {
        controllers.clickOpenContactListPageButton();
        return PageManager.createInstance(page, ListPage.class);
    }

    @Step("check logging out from ContactDetailsPage")
    public LoginPage logout() {
        controllers.clickLogoutButton();
        return PageManager.createInstance(page, LoginPage.class);
    }


}
