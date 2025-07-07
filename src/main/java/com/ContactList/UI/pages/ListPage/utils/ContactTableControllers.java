package com.ContactList.UI.pages.ListPage.utils;

import com.ContactList.API.core.payloads.ContactsPayloads.ContactsBodyPayload;
import com.ContactList.UI.BaseClasses.BaseComponent;
import com.ContactList.UI.pages.ContactDetailsPage.utils.ContactDetailsFormControllers;
import com.ContactList.UI.utils.customUtils.WaitUtils;
import com.ContactList.UI.utils.endpoints.PageEndpoints;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import lombok.Getter;

import java.util.concurrent.ThreadLocalRandom;

public class ContactTableControllers extends BaseComponent {

    public ContactTableControllers(Page page) {
        super(page);
    }

    @Getter
    private static final String table = "table#myTable";
    @Getter
    private static final String tableRows = "tr.contactTableBodyRow";

    public int getAmountOfRows() {
        return page.locator(table + " " + tableRows).count();
    }

    /**
     * May be deprecated
     */
    public Locator getCell(Locator row, int cell) {
        return row.locator("td:not([hidden])").nth(cell);
    }

    public void clickRow(int rowIndex1Based) {
        Locator rows = page.locator(tableRows);

        int totalRows = rows.count();
        if (rowIndex1Based < 1 || rowIndex1Based > totalRows) {
            throw new IllegalArgumentException("Row index out of bounds: " + rowIndex1Based);
        }

        rows.nth(rowIndex1Based - 1).click();
        WaitUtils.waitForPageURL(page, PageEndpoints.CONTACT_DETAILS);
    }

    public void clickRandomRow() {
        clickRow(ThreadLocalRandom.current().nextInt(1, getAmountOfRows() + 1));
        WaitUtils.waitUntilElementIsDisplayed(page, ContactDetailsFormControllers.getContactForm());
    }

    public ContactsBodyPayload getContactData(int row) {
        Locator rowlocator = page.locator(tableRows).nth(row -1);
        return ContactPayloadBuilder.fromRow(rowlocator);
    }
}
