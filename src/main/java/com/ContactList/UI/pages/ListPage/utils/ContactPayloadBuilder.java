package com.ContactList.UI.pages.ListPage.utils;

import com.ContactList.API.core.payloads.ContactsPayloads.ContactsBodyPayload;
import com.ContactList.UI.pages.ContactDetailsPage.utils.ContactDetailsFormControllers;
import com.ContactList.UI.utils.customUtils.mappers.Mappers;
import com.microsoft.playwright.Locator;

public class ContactPayloadBuilder {

    private static void populateContactName(Locator rowLocator, ContactsBodyPayload payload) {
        String[] nameParts = rowLocator.locator("td:not([hidden])")
                .nth(TableHeaders.NAME.getIndex())
                .innerText()
                .split(" ");
        payload.setFirstName(nameParts.length > 0 ? nameParts[0] : "");
        payload.setLastName(nameParts.length > 1 ? nameParts[1] : "");
    }

    private static void populateCityStatePostalCode(Locator rowLocator, ContactsBodyPayload payload) {
        String[] parts = rowLocator.locator("td:not([hidden])")
                .nth(TableHeaders.CITY_STATE_POSTAL_CODE.getIndex())
                .innerText()
                .split(" ");
        payload.setCity(parts.length > 0 ? parts[0] : "");
        payload.setStateProvince(parts.length > 1 ? parts[1] : "");
        payload.setPostalCode(parts.length > 2 ? parts[2] : "");
    }

    private static void populateDynamicFields(Locator rowLocator, ContactsBodyPayload payload) {
        String[] parts = rowLocator.locator("td:not([hidden])")
                .nth(TableHeaders.ADDRESS.getIndex())
                .innerText()
                .split(" ");

        if (parts.length > 0 && !parts[0].isBlank()) {
            payload.getDynamicFields()
                    .put(ContactDetailsFormControllers.getSTREET1_KEY(),parts[0]);
        }

        if (parts.length > 1 && !parts[1].isBlank()) {
            payload.getDynamicFields()
                    .put(ContactDetailsFormControllers.getSTREET2_KEY(),parts[1]);
        }
    }

    public static ContactsBodyPayload fromRow(Locator rowLocator) {
        ContactsBodyPayload payload = new ContactsBodyPayload();

        Mappers.getTABLE_FIELD_MAPPINGS().forEach((tableColumn, biconsumer) -> {
            String cellText = rowLocator.locator("td:not([hidden])")
                    .nth(tableColumn.getIndex())
                    .innerText();
            biconsumer.accept(payload,cellText);
        });

        populateContactName(rowLocator, payload);
        populateCityStatePostalCode(rowLocator, payload);
        populateDynamicFields(rowLocator, payload);
        return payload;
    }
}
