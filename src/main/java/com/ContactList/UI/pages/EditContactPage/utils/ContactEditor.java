package com.ContactList.UI.pages.EditContactPage.utils;

import com.ContactList.API.core.payloads.ContactsPayloads.ContactsBodyPayload;
import com.ContactList.utils.mappers.Mappers;
import com.microsoft.playwright.Page;

import java.util.Map;
import java.util.Optional;

import static com.ContactList.UI.pages.EditContactPage.utils.EditContactControllers.editContactForm;
import static com.ContactList.utils.mappers.Mappers.CONTACT_PAYLOAD_ENTRY_MAPPING;

public class ContactEditor {

    /**
     * <p>Method used to fully Edit the contact on the EditContactPage using the presented Data in the replacementPayload<p>
     *     <p> uses custom {@code FORM_FIELD_EDITOR_MAPPINGS()} to
     *     traverse through all possible fields (keys) and set (values)
     *     predefined values from the passed in payload
     */

    public static void editContact(Page page, ContactsBodyPayload replacementPayload) {
        Mappers.getFORM_FIELD_EDITOR_MAPPINGS()
                .forEach((selector, setter) -> page.locator(editContactForm + " " + selector)
                .fill(Optional.ofNullable(setter.apply(replacementPayload))
                        .orElse("")));

        setStreetFields(page,replacementPayload);
    }

    /**
     * <p>CONTEXT -> this partial edit method helps to edit only the fields on the EditContactPage page
     * which were specified in the passed-in payload<p>
     * <p> This becomes possible with the help of the {@code CONTACT_PAYLOAD_ENTRY_MAPPING} method<p>
     *     <p> that returns a Map with key / value pairs of the populated and non-null fields in the passed-in payload<p>
     *         <p> Later -> stream API helps to walk through each entry and fill the field (which is found using "key") with the value from the obtained Map<p>
     * <p> Page object is used to consistency preserve the browser session naturally and perform all the needed actions<p>
     *
     * @param page -> page instance used to perform actions on the page
     * @param replacementPayload -> payload with replacement data which will be
     *                           used to fill fields on the page in relation with populated non-null fields from the payload
     */
    public static void partialEditContact(Page page, ContactsBodyPayload replacementPayload) {
        Map<String, String> availableFields = CONTACT_PAYLOAD_ENTRY_MAPPING(replacementPayload);
        availableFields
                .forEach((key, value) -> page.locator(editContactForm + " " + key)
                .fill(value));

        setStreetFields(page,replacementPayload);
    }

    /**
     * Complimentary method for {@code partialEditContact()} and {@code editContact()} method
     * which obtains the data from the passed-in payload
     * and fills corresponding fields using all non-null and non-blank dynamic fields
     * form the passed-in payload
     */

    private static void setStreetFields(Page page, ContactsBodyPayload payload) {
        Map<String,String> dynamicFields = payload.getDynamicFields();

        for (Map.Entry<String, String> entry : dynamicFields.entrySet()) {
            if (entry.getValue() != null && !entry.getValue().isBlank()) {
                page.locator("#" + entry.getKey()).fill(entry.getValue());
            }
        }
    }
}
