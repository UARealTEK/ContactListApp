package com.ContactList.UI.pages.EditContactPage.utils;

import com.ContactList.API.core.payloads.ContactsPayloads.ContactsBodyPayload;
import com.ContactList.UI.utils.customUtils.mappers.Mappers;
import com.microsoft.playwright.Page;

import java.util.Map;
import java.util.Optional;

import static com.ContactList.UI.pages.EditContactPage.utils.EditContactControllers.editContactForm;
import static com.ContactList.UI.utils.customUtils.mappers.Mappers.CONTACT_PAYLOAD_ENTRY_MAPPING;

public class ContactEditor {

    public static void editContact(Page page, ContactsBodyPayload replacementPayload) {
        Mappers.getFORM_FIELD_EDITOR_MAPPINGS().forEach((selector, setter) -> {
            page.locator(editContactForm + " " + selector)
                    .fill(Optional.ofNullable(setter.apply(replacementPayload))
                            .orElse(""));
        });

        setStreetFields(page,replacementPayload);
    }

    public static void partialEditContact(Page page, ContactsBodyPayload replacementPayload) {
        Map<String, String> availableFields = CONTACT_PAYLOAD_ENTRY_MAPPING(replacementPayload);
        availableFields
                .forEach((key, value) -> page.locator(editContactForm + " " + key)
                .fill(value));

        setStreetFields(page,replacementPayload);
    }

    private static void setStreetFields(Page page, ContactsBodyPayload payload) {
        Map<String,String> dynamicFields = payload.getDynamicFields();

        for (Map.Entry<String, String> entry : dynamicFields.entrySet()) {
            if (entry.getValue() != null && !entry.getValue().isBlank()) {
                page.locator("#" + entry.getKey()).fill(entry.getValue());
            }
        }
    }
}
