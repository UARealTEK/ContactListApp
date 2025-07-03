package com.ContactList.UI.pages.ContactDetailsPage.utils;

import com.ContactList.API.core.payloads.ContactsPayloads.ContactsBodyPayload;
import com.ContactList.UI.BaseClasses.BaseComponent;
import com.microsoft.playwright.Page;

import java.util.Map;
import java.util.function.BiConsumer;

public class ContactDetailsFormControllers extends BaseComponent {

    public ContactDetailsFormControllers(Page page) {
        super(page);
    }

    private static final String contactForm = "#contactDetails";
    private static final String street1 = contactForm + " #street1";
    private static final String street2 = contactForm + " #street2";

    private static final Map<String, BiConsumer<ContactsBodyPayload, String>> FIELD_MAPPINGS = Map.of(
            "#firstName", ContactsBodyPayload::setFirstName,
            "#lastName", ContactsBodyPayload::setLastName,
            "#birthdate", ContactsBodyPayload::setBirthdate,
            "#email", ContactsBodyPayload::setEmail,
            "#phone", ContactsBodyPayload::setPhone,
            "#city", ContactsBodyPayload::setCity,
            "#stateProvince", ContactsBodyPayload::setStateProvince,
            "#postalCode", ContactsBodyPayload::setPostalCode,
            "#country", ContactsBodyPayload::setCountry
    );

    public ContactsBodyPayload getContactPayload() {
        ContactsBodyPayload payload = new ContactsBodyPayload();

        FIELD_MAPPINGS.forEach((selector, setter) -> {
            String value = page.locator(contactForm + " " + selector).innerText();
            setter.accept(payload,value);
        });

        setStreetFields(payload);
        return payload;
    }

    private void setStreetFields(ContactsBodyPayload payload) {
        Map<String, String> streetFields = Map.of(
               street1.substring(2), page.locator(street1).innerText(),
               street2.substring(2), page.locator(street2).innerText()
        );

        streetFields.forEach((field, value) -> {
            if (!value.isEmpty()) {
                payload.getDynamicFields().put(field,value);
            }
        });
    }
}
