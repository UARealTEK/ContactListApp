package com.ContactList.UI.pages.ContactDetailsPage.utils;

import com.ContactList.API.core.payloads.ContactsPayloads.ContactsBodyPayload;
import com.ContactList.UI.BaseClasses.BaseComponent;
import com.ContactList.UI.utils.customUtils.mappers.Mappers;
import com.microsoft.playwright.Page;
import lombok.Getter;

import java.util.Map;

public class ContactDetailsFormControllers extends BaseComponent {

    public ContactDetailsFormControllers(Page page) {
        super(page);
    }

    @Getter
    private static final String contactForm = "#contactDetails";

    /**
     * Dynamic fields that are involved in "Address" field in the Contact List Table (col-4)
     */

    @Getter
    private static final String street1 = contactForm + " #street1";
    @Getter
    private static final String street2 = contactForm + " #street2";
    @Getter
    private static final String STREET1_KEY = "street1";
    @Getter
    private static final String STREET2_KEY = "street2";


    public ContactsBodyPayload getContactPayload() {
        ContactsBodyPayload payload = new ContactsBodyPayload();

        Mappers.getFORM_FIELD_MAPPINGS().forEach((selector, setter) -> {
            String value = page.locator(contactForm + " " + selector).innerText().trim();
            setter.accept(payload,value);
        });

        setStreetFields(payload);
        return payload;
    }

    private void setStreetFields(ContactsBodyPayload payload) {
        Map<String, String> streetFields = Map.of(
               street1.substring(contactForm.length() + 2), page.locator(street1).innerText().trim(),
               street2.substring(contactForm.length() + 2), page.locator(street2).innerText().trim()
        );

        streetFields.forEach((field, value) -> {
            if (!value.isEmpty()) {
                payload.getDynamicFields().put(field,value);
            }
        });
    }
}
