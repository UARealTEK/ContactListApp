package com.ContactList.UI.pages.ListPage.utils;

import com.ContactList.API.core.payloads.ContactsPayloads.ContactsBodyPayload;
import com.ContactList.UI.BaseClasses.BaseComponent;
import com.ContactList.UI.utils.endpoints.PageEndpoints;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;
import lombok.Getter;

public class ListPageControllers extends BaseComponent {

    @Getter
    private final ContactTableController tableController;

    public ListPageControllers(Page page) {
        super(page);
        this.tableController = new ContactTableController();
    }

    /**
     * Inner table class for navigating within the table of available contacts for the specific user
     */

    public class ContactTableController {
        @Getter
        private static final String table = "id=myTable";
        @Getter
        private static final String tableRows = table + " > tr.contactTable-Body";

        public String getSpecificContactName(int row) {
            if (page.locator(tableRows).count() > 0) {
                return page.locator(tableRows).nth(row)
                        .locator("td").nth(1)
                        .innerText();
            } else throw new AssertionError("The row number #" + row + " does not exist in the table");
        }

        public String getSpecificContactBirthday(int row) {
            if (page.locator(tableRows).count() > 0) {
                return page.locator(tableRows).nth(row)
                        .locator("td").nth(2)
                        .innerText();
            } else throw new AssertionError("The row number #" + row + " does not exist in the table");
        }

        public String getSpecificContactEmail(int row) {
            if (page.locator(tableRows).count() > 0) {
                return page.locator(tableRows).nth(row)
                        .locator("td").nth(3)
                        .innerText();
            } else throw new AssertionError("The row number #" + row + " does not exist in the table");
        }

        public String getSpecificContactPhone(int row) {
            if (page.locator(tableRows).count() > 0) {
                return page.locator(tableRows).nth(row)
                        .locator("td").nth(4)
                        .innerText();
            } else throw new AssertionError("The row number #" + row + " does not exist in the table");
        }

        public String getSpecificContactAddress(int row) {
            if (page.locator(tableRows).count() > 0) {
                return page.locator(tableRows).nth(row)
                        .locator("td").nth(5)
                        .innerText();
            } else throw new AssertionError("The row number #" + row + " does not exist in the table");
        }

        public String getSpecificContactCityStatePostalCode(int row) {
            if (page.locator(tableRows).count() > 0) {
                return page.locator(tableRows).nth(row)
                        .locator("td").nth(6)
                        .innerText();
            } else throw new AssertionError("The row number #" + row + " does not exist in the table");
        }

        public String getSpecificContactCountry(int row) {
            if (page.locator(tableRows).count() > 0) {
                return page.locator(tableRows).nth(row)
                        .locator("td").nth(7)
                        .innerText();
            } else throw new AssertionError("The row number #" + row + " does not exist in the table");
        }

        //TODO:
        // finish tis method so it meets the following requirements:
        // - It handles both RICH and standard payloads (with and without Street address)
        // - It handles clear field separation of City,State,PostalCode. Refactor it.
        // No guarantee that StateProvince will contain only 1 word
        public ContactsBodyPayload getSpecificContactData(int row) {
            ContactsBodyPayload payload = new ContactsBodyPayload();

            String[] fullName = getSpecificContactName(row).split(" ");
            payload.setFirstName(fullName.length > 0 ? fullName[0] : "");
            payload.setLastName(fullName.length > 1 ? fullName[1] : "");

            payload.setBirthdate(getSpecificContactBirthday(row));
            payload.setEmail(getSpecificContactEmail(row));
            payload.setPhone(getSpecificContactPhone(row));

            String[] cityStatePostalCode = getSpecificContactCityStatePostalCode(row).split(" ");
            payload.setCity(cityStatePostalCode.length > 0 ? cityStatePostalCode[0] : "");
            payload.setStateProvince(cityStatePostalCode.length > 1 ? cityStatePostalCode[1] : "");
            payload.setPostalCode(cityStatePostalCode.length > 2 ? cityStatePostalCode[2] : "");

            payload.setCountry(getSpecificContactCountry(row));

            return payload;
        }
    }

    /**
     * Inner locator class for local usage
     */

    private static final class Locators {
        private static final String ADD_CONTACT = "id=add-contact";
        private static final String LOGOUT = "id=logout";
    }

    public void clickAddContactButton() {
        page.locator(Locators.ADD_CONTACT).click();

        try {
            page.waitForURL(PageEndpoints.getFullAddContactURL());
        } catch (PlaywrightException e) {
            throw new AssertionError("The Add Contact page was not opened." +
                    " Expected URL to be -> " + PageEndpoints.getFullAddContactURL());
        }
    }

    public void clickLogoutButton() {
        page.locator(Locators.LOGOUT).click();

        try {
            page.waitForURL(PageEndpoints.getFullLoginURL());
        } catch (PlaywrightException e) {
            throw new AssertionError("The Login page was not opened." +
                    " Expected URL to be -> " + PageEndpoints.getFullLoginURL());
        }
    }
}
