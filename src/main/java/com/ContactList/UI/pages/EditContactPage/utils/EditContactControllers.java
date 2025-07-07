package com.ContactList.UI.pages.EditContactPage.utils;

import com.ContactList.UI.BaseClasses.BaseComponent;
import com.ContactList.UI.utils.customUtils.WaitUtils;
import com.ContactList.UI.utils.customUtils.mappers.Mappers;
import com.ContactList.UI.utils.endpoints.PageEndpoints;
import com.microsoft.playwright.Page;
import lombok.Getter;

public class EditContactControllers extends BaseComponent {

    public EditContactControllers(Page page) {
        super(page);
    }

    @Getter
    public static final String editContactForm = "#edit-contact";
    @Getter
    public static final String submit = "#submit";
    @Getter
    public static final String cancel = "#cancel";

    public void clickSubmitButton() {
        page.locator(submit).click();
        WaitUtils.waitForPageURL(page, PageEndpoints.CONTACT_DETAILS);
        WaitUtils.waitForAll(page, Mappers.getFORM_FIELD_MAPPINGS().keySet().stream().toList());
    }

    public void clickCancelButton() {
        page.locator(cancel).click();
        WaitUtils.waitForPageURL(page, PageEndpoints.CONTACT_DETAILS);
        WaitUtils.waitForAll(page, Mappers.getFORM_FIELD_MAPPINGS().keySet().stream().toList());
    }
}
