package com.ContactList.UI.pages.ContactDetailsPage.utils;

import com.ContactList.API.core.payloads.ContactsPayloads.ContactsBodyPayload;
import com.ContactList.UI.BaseClasses.BaseComponent;
import com.ContactList.UI.utils.customUtils.waitUtils.WaitUtils;
import com.ContactList.utils.mappers.Mappers;
import com.microsoft.playwright.Page;
import lombok.Getter;

import java.util.Map;

public class ContactDetailsFormControllers extends BaseComponent {

    public ContactDetailsFormControllers(Page page) {
        super(page);
    }

    /**
     * made this variable public to grant other utilities access to it
     */
    @Getter
    public static final String contactForm = "#contactDetails";

    /**
     * Dynamic fields that are involved in "Address" field in the Contact List Table (col-4)
     */

    @Getter
    protected static final String street1 = contactForm + " #street1";
    @Getter
    protected static final String street2 = contactForm + " #street2";
    @Getter
    protected static final String STREET1_KEY = "street1";
    @Getter
    protected static final String STREET2_KEY = "street2";

    /**
     * FYI -> {@code getContactPayload} method waits for all <>span</> elements (generated during runtime) and THEN proceeds to scrape the data
     * @return -> computed payload which was obtained from the displayed data on the ContactDetails page
     */
    public ContactsBodyPayload getContactPayload() {
        ContactsBodyPayload payload = new ContactsBodyPayload();

        WaitUtils.waitForAll(page,Mappers.getFORM_FIELD_MAPPINGS().keySet().stream().toList());
        Mappers.getFORM_FIELD_MAPPINGS().forEach((selector, setter) -> {
            String value = page.locator(contactForm + " " + selector).innerText();
            setter.accept(payload,value);
        });

        setStreetFields(payload);
        return payload;
    }

    private void setStreetFields(ContactsBodyPayload payload) {
        Map<String, String> streetFields = Map.of(
               STREET1_KEY, page.locator(street1).innerText(),
               STREET2_KEY, page.locator(street2).innerText()
        );

        streetFields.forEach((field, value) -> {
            if (!value.isEmpty()) {
                payload.getDynamicFields().put(field,value);
            }
        });
    }
}
