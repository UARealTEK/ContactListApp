package com.ContactList.UI.pages.ListPage.utils;

import com.ContactList.API.core.payloads.ContactsPayloads.ContactsBodyPayload;
import com.ContactList.UI.BaseClasses.BaseComponent;
import com.ContactList.UI.pages.ContactDetailsPage.utils.ContactDetailsFormControllers;
import com.ContactList.UI.utils.customUtils.WaitUtils;
import com.ContactList.UI.utils.endpoints.PageEndpoints;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import lombok.Getter;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BiConsumer;

//TODO: optimize readability to follow SRP, readability, maintainability
public class ContactTableControllers extends BaseComponent {

    public ContactTableControllers(Page page) {
        super(page);
    }

    @Getter
    private static final String table = "table#myTable";
    @Getter
    private static final String tableRows = "tr.contactTableBodyRow";

    private static final Map<TableHeaders, BiConsumer<ContactsBodyPayload, String>> FIELD_MAPPINGS = Map.of(
            TableHeaders.BIRTHDATE,ContactsBodyPayload::setBirthdate,
            TableHeaders.EMAIL, ContactsBodyPayload::setEmail,
            TableHeaders.PHONE, ContactsBodyPayload::setPhone,
            TableHeaders.COUNTRY, ContactsBodyPayload::setCountry
    );

    public int getAmountOfRows() {
        return page.locator(tableRows).count();
    }

    private Locator getCell(int row, int cell) {
        return page.locator(tableRows)
                .nth(row - 1)
                .locator("td:not([hidden])")
                .nth(cell);
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
        WaitUtils.waitForPageURL(page,PageEndpoints.CONTACT_DETAILS);
    }

    /**
     *
     * @param row -> row for each we are obtaining contact name
     * @return contact payload filled only with FirstName / LastName
     *
     * <p>FYI: not checking for null since FirstName / LastName fields are mandatory<p>
     */
    private ContactsBodyPayload getContactName(int row) {
        ContactsBodyPayload payload = new ContactsBodyPayload();
        String[] nameParts = getCell(row, TableHeaders.NAME.getIndex()).innerText().split(" ");
        payload.setFirstName(nameParts.length > 0 ? nameParts[0] : "");
        payload.setLastName(nameParts.length > 1 ? nameParts[1] : "");
        return payload;
    }

    private ContactsBodyPayload getCityStatePostalCode(int row) {
        ContactsBodyPayload payload = new ContactsBodyPayload();
        String[] parts = getCell(row, TableHeaders.CITY_STATE_POSTAL_CODE.getIndex()).innerText().split(" ");
        payload.setCity(parts.length > 0 ? parts[0] : "");
        payload.setStateProvince(parts.length > 1 ? parts[1] : "");
        payload.setPostalCode(parts.length > 2 ? parts[2] : "");

        return payload;
    }

    private void setDynamicFields(int row, ContactsBodyPayload payload) {
        String[] parts = getCell(row, TableHeaders.ADDRESS.getIndex()).innerText().split(" ");

        if (parts.length > 0 && !parts[0].isBlank()) {
            payload.getDynamicFields().put(ContactDetailsFormControllers.getStreet1().substring(ContactDetailsFormControllers.getContactForm().length() + 2),parts[0]);
        }

        if (parts.length > 1 && !parts[1].isBlank()) {
            payload.getDynamicFields().put(ContactDetailsFormControllers.getStreet2().substring(ContactDetailsFormControllers.getContactForm().length() + 2),parts[1]);
        }
    }

    public ContactsBodyPayload getContactData(int row) {
        ContactsBodyPayload payload = new ContactsBodyPayload();
        payload.setFirstName(getContactName(row).getFirstName());
        payload.setLastName(getContactName(row).getLastName());
        payload.setCity(getCityStatePostalCode(row).getCity());
        payload.setStateProvince(getCityStatePostalCode(row).getStateProvince());
        payload.setPostalCode(getCityStatePostalCode(row).getPostalCode());

        FIELD_MAPPINGS.forEach((tableColumn, biconsumer) -> {
            String cellText = getCell(row,tableColumn.getIndex()).innerText();
            biconsumer.accept(payload,cellText);
        });

        setDynamicFields(row,payload);

        return payload;
    }
}
