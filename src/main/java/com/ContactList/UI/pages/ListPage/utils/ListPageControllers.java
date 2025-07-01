package com.ContactList.UI.pages.ListPage.utils;

import com.ContactList.API.core.payloads.ContactsPayloads.ContactsBodyPayload;
import com.ContactList.UI.BaseClasses.BaseComponent;
import com.ContactList.UI.utils.endpoints.PageEndpoints;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.WaitForSelectorState;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//TODO: optimize code inside ContactTableController class
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
        private static final String table = "table#myTable";
        @Getter
        private static final String tableRows = table + " > tr.contactTableBodyRow";

        public void waitUntilTableIsDisplayed() {
            page.locator(table)
                    .waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        }

        public int getAmountOfRows() {
            return page.locator(tableRows).count();
        }

        public void clickRandomRow() {
            int amountOfRows = getAmountOfRows();
            if (amountOfRows > 0) {
                Random random = new Random();
                int rowNum = random.nextInt(getAmountOfRows());
                page.locator(tableRows).nth(rowNum).click();
                try {
                    page.waitForURL(PageEndpoints.getFullContactDetailsURL());
                } catch (PlaywrightException e) {
                    throw new AssertionError("The page URL has not changed after the click. " +
                            "expected URL to be -> " + PageEndpoints.getFullContactDetailsURL(), e);
                }
            } else throw new AssertionError("The row number #" + amountOfRows + " does not exist in the table");
        }

        public void clickSpecificRow(int row) {
            if (getAmountOfRows() > 0) {
                page.locator(tableRows).nth(row)
                        .click();
            } else throw new AssertionError("The row number #" + row + " does not exist in the table");
        }

        public String getSpecificContactName(int row) {
            if (getAmountOfRows() > 0) {
                return page.locator(tableRows).nth(row)
                        .locator("td").nth(1)
                        .innerText();
            } else throw new AssertionError("The row number #" + row + " does not exist in the table");
        }

        public String getSpecificContactBirthday(int row) {
            if (getAmountOfRows() > 0) {
                return page.locator(tableRows).nth(row)
                        .locator("td").nth(2)
                        .innerText();
            } else throw new AssertionError("The row number #" + row + " does not exist in the table");
        }

        public String getSpecificContactEmail(int row) {
            if (getAmountOfRows() > 0) {
                return page.locator(tableRows).nth(row)
                        .locator("td").nth(3)
                        .innerText();
            } else throw new AssertionError("The row number #" + row + " does not exist in the table");
        }

        public String getSpecificContactPhone(int row) {
            if (getAmountOfRows() > 0) {
                return page.locator(tableRows).nth(row)
                        .locator("td").nth(4)
                        .innerText();
            } else throw new AssertionError("The row number #" + row + " does not exist in the table");
        }

        public String getSpecificContactAddress(int row) {
            if (getAmountOfRows() > 0) {
                return page.locator(tableRows).nth(row)
                        .locator("td").nth(5)
                        .innerText();
            } else throw new AssertionError("The row number #" + row + " does not exist in the table");
        }

        public String getSpecificContactCityStatePostalCode(int row) {
            if (getAmountOfRows() > 0) {
                return page.locator(tableRows).nth(row)
                        .locator("td").nth(6)
                        .innerText();
            } else throw new AssertionError("The row number #" + row + " does not exist in the table");
        }

        public String getSpecificContactCountry(int row) {
            if (getAmountOfRows() > 0) {
                return page.locator(tableRows).nth(row)
                        .locator("td").nth(7)
                        .innerText();
            } else throw new AssertionError("The row number #" + row + " does not exist in the table");
        }

        public List<String> getSpecificTableRowData(int row) {
            ArrayList<String> result = new ArrayList<>();

            result.add(getSpecificContactName(row));
            result.add(getSpecificContactBirthday(row));
            result.add(getSpecificContactEmail(row));
            result.add(getSpecificContactPhone(row));
            result.add(getSpecificContactAddress(row));
            result.add(getSpecificContactCityStatePostalCode(row));
            result.add(getSpecificContactCountry(row));

            return result;
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
