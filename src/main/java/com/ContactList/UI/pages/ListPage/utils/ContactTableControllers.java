package com.ContactList.UI.pages.ListPage.utils;

import com.ContactList.UI.BaseClasses.BaseComponent;
import com.ContactList.UI.utils.controllerUtils.WaitUtils;
import com.ContactList.UI.utils.endpoints.PageEndpoints;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ContactTableControllers extends BaseComponent {

    public ContactTableControllers(Page page) {
        super(page);
    }

    @Getter
    private static final String table = "table#myTable";
    @Getter
    private static final String tableRows = table + " > tr.contactTableBodyRow";

    private void assertRowExist(int row) {
        if (row <= 0 || row > getAmountOfRows()) {
            throw new AssertionError("row " + row + "does not exist");
        }
    }

    public int getAmountOfRows() {
        return page.locator(tableRows).count();
    }

    private Locator getCell(int row, int cell) {
        return page.locator(tableRows).nth(row).locator("td").nth(cell);
    }

    public void clickRow(int row) {
        page.locator(tableRows).nth(row).click();
    }

    public void clickRandomRow() {
        Random random = new Random();
        int row = random.nextInt(getAmountOfRows());
        clickRow(row);
        WaitUtils.waitForPageURL(page,PageEndpoints.CONTACT_DETAILS);
    }

    public void clickSpecificRow(int row) {
        assertRowExist(row);
        clickRow(row);
        WaitUtils.waitForPageURL(page,PageEndpoints.CONTACT_DETAILS);
    }

    public String getSpecificContactName(int row) {
        assertRowExist(row);
        return getCell(row,TableHeaders.NAME.getIndex()).innerText();
    }

    public String getSpecificContactBirthday(int row) {
        assertRowExist(row);
        return getCell(row,TableHeaders.BIRTHDATE.getIndex()).innerText();
    }

    public String getSpecificContactEmail(int row) {
        assertRowExist(row);
        return getCell(row,TableHeaders.EMAIL.getIndex()).innerText();
    }

    public String getSpecificContactPhone(int row) {
        assertRowExist(row);
        return getCell(row,TableHeaders.PHONE.getIndex()).innerText();
    }

    public String getSpecificContactAddress(int row) {
        assertRowExist(row);
        return getCell(row,TableHeaders.ADDRESS.getIndex()).innerText();
    }

    public String getSpecificContactCityStatePostalCode(int row) {
        assertRowExist(row);
        return getCell(row,TableHeaders.CITY_STATE_POSTAL_CODE.getIndex()).innerText();
    }

    public String getSpecificContactCountry(int row) {
        assertRowExist(row);
        return getCell(row,TableHeaders.COUNTRY.getIndex()).innerText();
    }

    public List<String> getSpecificTableRowData(int row) {
        assertRowExist(row);
        ArrayList<String> result = new ArrayList<>();

        for (TableHeaders headers : TableHeaders.values()) {
            result.add(getCell(row,headers.getIndex()).innerText());
        }

        return result;
    }
}
