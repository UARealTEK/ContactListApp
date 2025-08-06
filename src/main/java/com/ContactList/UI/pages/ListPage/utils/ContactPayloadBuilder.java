package com.ContactList.UI.pages.ListPage.utils;

import com.ContactList.API.core.payloads.ContactsPayloads.ContactsBodyPayload;
import com.ContactList.UI.pages.ContactDetailsPage.utils.ContactDetailsFormControllers;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.util.stream.IntStream;

/**
 * Custom utility used to gather the data from the specific Table row and present it in well-composed
 * {@code ContactBodyPayload}
 */
public class ContactPayloadBuilder {

    /**
     * First supplementary method used to obtain the INDEX of the specified column based on the provided
     * Header name column
     *
     * @param page -> needed for working with the current Page instance. Passed around
     * @param headers -> exact header we want to look for in order to obtain an ID
     *                Further will be used to pass into {@code getColumnText()} method
     * @return -> ID of the specified header column
     */
    private static int getColumnIndex(Page page, TableHeaders headers) {
        Locator headerCells = page.locator(ContactTableControllers.getTableHeaders());

        return IntStream.range(0, headerCells.count())
                .filter(i -> headerCells.nth(i).innerText().equalsIgnoreCase(headers.getColumnName()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Index not found for header: " + headers.getColumnName()));
    }

    /**
     * Second supplementary method used to obtain the TEXT of the specified row cell based on the
     * provided Header name column
     *
     * @param page -> needed for working with the current Page instance. Passed around
     * @param rowLocator -> row for which we need to obtain the text
     * @param headers -> exact column name for which we need to obtain the text
     * @return -> String representation for the specified column which is located at specified {@code Locator} row
     */
    private static String getColumnText(Page page, Locator rowLocator, TableHeaders headers) {
        int index = getColumnIndex(page,headers);
        return rowLocator.locator("td:not([hidden])").nth(index).innerText();
    }

    /**
     *
     * @param page -> needed for working with the current Page instance. Passed around
     * @param rowLocator -> row for which we need to populate data
     * @param payload -> updated {@code ContactsBodyPayload} object that will be passed further
     */
    private static void populateContactName(Page page, Locator rowLocator, ContactsBodyPayload payload) {
        String[] nameParts = getColumnText(page, rowLocator, TableHeaders.NAME).split(" ");
        payload.setFirstName(nameParts.length > 0 ? nameParts[0] : "");
        payload.setLastName(nameParts.length > 1 ? nameParts[1] : "");
    }

    /**
     *
     * @param page -> needed for working with the current Page instance. Passed around
     * @param rowLocator -> row for which we need to populate data
     * @param payload ->  updated {@code ContactsBodyPayload} object that will be passed further
     */
    private static void populateCityStatePostalCode(Page page, Locator rowLocator, ContactsBodyPayload payload) {
        String[] parts = getColumnText(page,rowLocator,TableHeaders.CITY_STATE_POSTAL_CODE).split(" ");
        payload.setCity(parts.length > 0 ? parts[0] : "");
        payload.setStateProvince(parts.length > 1 ? parts[1] : "");
        payload.setPostalCode(parts.length > 2 ? parts[2] : "");
    }

    /**
     *
     * @param page -> needed for working with the current Page instance. Passed around
     * @param rowLocator -> row for which we need to populate data
     * @param payload ->  updated {@code ContactsBodyPayload} object that will be passed further
     */
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

    /**
     * Main method that is used to compose the {@code ContactsBodyPayload} object which ill be passed further
     * for :
     * - Verification purposes
     * - can be used further to map to a DTO
     * - can be used to fill up the contact on the {@code EditContactPage}
     * <p> --- </p>
     * Contains of multiple methods from this class that gather the data from the defined filed: <p> </p>
     * - {@code populateContactName()} <p> </p>
     * - {@code populateCityStatePostalCode()}
     * <p> </p>
     * - {@code populateDynamicFields()}
     * <p> </p>
     * @param page -> needed for working with the current Page instance. Passed around
     * @param rowLocator -> row for which we need to populate data
     * @return -> fully populated payload with data obtained from the specified row from the Contacts Table
     */
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
