package com.ContactList.UI.utils.controllerUtils;

import com.ContactList.UI.utils.endpoints.PageEndpoints;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;
import com.microsoft.playwright.options.WaitForSelectorState;

import static com.ContactList.UI.utils.Managers.ConfigurationManager.config;

public class WaitUtils {

    public static void waitUntilElementIsDisplayed(Page page, String elementPath) {
        try {
            page.locator(elementPath)
                    .waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        } catch (PlaywrightException e) {
            throw new AssertionError("The passed in locator selector -> " + elementPath
            + " was not displayed in time");
        }
    }

    public static void waitForPageURL(Page page, PageEndpoints endpoint) {
        String fullPath = config().baseURL() + endpoint.getEndpoint();
        try {
            page.waitForURL(fullPath);
        } catch (PlaywrightException e) {
            throw new AssertionError("The page URL has not changed after the click. " +
                    "expected URL to be -> " + fullPath, e);
        }
    }
}
