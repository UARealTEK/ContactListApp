package com.ContactList.UI.pages.ListPage.utils;

import com.ContactList.API.core.payloads.ContactsPayloads.ContactsBodyPayload;
import com.ContactList.UI.pages.ContactDetailsPage.utils.ContactDetailsFormControllers;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.util.stream.IntStream;

/**
 * custom utility used to gather the data from the specific Table row and present it in well-composed ContactBodyPayload
 */
// TODO: test fromRow(Page page, Locator rowLocator)
// TODO: create tests for comparing API response (data that was received after opening the contact page / table) with displayed UI data
public class ContactPayloadBuilder {

    private static int getColumnIndex(Page page, TableHeaders headers) {
        Locator headerCells = page.locator(ContactTableControllers.getTableHeaders());

        return IntStream.range(0, headerCells.count())
                .filter(i -> headerCells.nth(i).innerText().equalsIgnoreCase(headers.getColumnName()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Index not found for header: " + headers.getColumnName()));
    }

    private static String getColumnText(Page page, Locator rowLocator, TableHeaders headers) {
        int index = getColumnIndex(page,headers);
        return rowLocator.locator("td:not([hidden])").nth(index).innerText();
    }

    private static void populateContactName(Page page, Locator rowLocator, ContactsBodyPayload payload) {
        String[] nameParts = getColumnText(page, rowLocator, TableHeaders.NAME).split(" ");
        payload.setFirstName(nameParts.length > 0 ? nameParts[0] : "");
        payload.setLastName(nameParts.length > 1 ? nameParts[1] : "");
    }

    private static void populateCityStatePostalCode(Page page, Locator rowLocator, ContactsBodyPayload payload) {
        String[] parts = getColumnText(page,rowLocator,TableHeaders.CITY_STATE_POSTAL_CODE).split(" ");
        payload.setCity(parts.length > 0 ? parts[0] : "");
        payload.setStateProvince(parts.length > 1 ? parts[1] : "");
        payload.setPostalCode(parts.length > 2 ? parts[2] : "");
    }

    private static void populateDynamicFields(Page page, Locator rowLocator, ContactsBodyPayload payload) {
        String[] parts = getColumnText(page, rowLocator, TableHeaders.ADDRESS).split(" ");

        if (parts.length > 0 && !parts[0].isBlank()) {
            payload.getDynamicFields()
                    .put(ContactDetailsFormControllers.getSTREET1_KEY(),parts[0]);
        }

        if (parts.length > 1 && !parts[1].isBlank()) {
            payload.getDynamicFields()
                    .put(ContactDetailsFormControllers.getSTREET2_KEY(),parts[1]);
        }
    }

    public static ContactsBodyPayload fromRow(Page page, Locator rowLocator) {
        ContactsBodyPayload payload = new ContactsBodyPayload();

        payload.setBirthdate(getColumnText(page,rowLocator,TableHeaders.BIRTHDATE));
        payload.setEmail(getColumnText(page,rowLocator,TableHeaders.EMAIL));
        payload.setPhone(getColumnText(page,rowLocator,TableHeaders.PHONE));
        payload.setCountry(getColumnText(page,rowLocator,TableHeaders.COUNTRY));
        populateContactName(page, rowLocator, payload);
        populateCityStatePostalCode(page, rowLocator, payload);
        populateDynamicFields(page, rowLocator, payload);
        return payload;
    }
}
