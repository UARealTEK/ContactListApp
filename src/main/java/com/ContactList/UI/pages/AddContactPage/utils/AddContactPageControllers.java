package com.ContactList.UI.pages.AddContactPage.utils;

import com.ContactList.API.core.payloads.ContactsPayloads.ContactsBodyPayload;
import com.ContactList.UI.BaseClasses.BaseComponent;
import com.ContactList.UI.pages.ListPage.utils.ContactTableControllers;
import com.ContactList.UI.utils.customUtils.WaitUtils;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import lombok.Getter;

import java.util.Map;

public class AddContactPageControllers extends BaseComponent {

    public AddContactPageControllers(Page page) {
        super(page);
    }

    @Getter
    private static final String SUBMIT = "id=submit";
    @Getter
    private static final String CANCEL = "id=cancel";

    /**
     * @param payload -> expected Contact Payload to add
     *
     * <p>Current approach allows passing in a flexible payload object:</p>
     * <ul>
     *   <li>Full Payload (Declared class fields + dynamic fields)</li>
     *   <li>Strict dedicated Payload (Only declared class fields)</li>
     *   <li>Partial Payload (Only fields which are != null in the field will be parsed and filled)</li>
     * </ul>
     *
     * <p>Partial payload may be useful for tests which aim to update only certain necessary fields.</p>
     * <p>===</p>
     * <p>===</p>
     * <p><strong>ISSUE:</strong> {@code fillContact()} method contains hardcoded values for field names.
     * Can be error-prone if amount of fields / field names on AddContactPage changes.</p>
     */

    public void fillContact(ContactsBodyPayload payload) {
        fillIfEmpty("firstName", payload.getFirstName());
        fillIfEmpty("lastName", payload.getLastName());
        fillIfEmpty("birthdate", payload.getBirthdate());
        fillIfEmpty("email", payload.getEmail());
        fillIfEmpty("phone", payload.getPhone());
        fillIfEmpty("city", payload.getCity());
        fillIfEmpty("stateProvince", payload.getStateProvince());
        fillIfEmpty("postalCode", payload.getPostalCode());
        fillIfEmpty("country", payload.getCountry());

        if (payload.getDynamicFields() != null && !payload.getDynamicFields().isEmpty()) {
            for (Map.Entry<String,String> field : payload.getDynamicFields().entrySet()) {
                fillIfEmpty(field.getKey(),field.getValue());
            }
        }
    }

    /**
     *
     * @param key -> represents field name which was passed in the payload
     *
     * @param value -> represents field value which was passed in the payload
     *              <p>{@code fillIfEmpty()} represents a helper method required in the {@code fillContact()} method to rule out empty / null values <p>
     */

    private void fillIfEmpty(String key, String value) {
        if (value == null || value.isEmpty()) {
            return;
        }

        Locator locator = page.locator("id=" + key);
        if (locator.count() > 0) {
            locator.fill(value);
        } else throw new AssertionError("The field -> " + key + " <- was not found on the page");
    }

    public void clickSubmit() {
        page.locator(SUBMIT).click();
        WaitUtils.waitUntilElementIsDisplayed(page, ContactTableControllers.getTable());
    }

    public void clickCancel() {
        page.locator(CANCEL).click();
    }

}
