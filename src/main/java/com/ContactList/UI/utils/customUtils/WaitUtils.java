package com.ContactList.UI.utils.customUtils;

import com.ContactList.UI.utils.endpoints.PageEndpoints;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;
import com.microsoft.playwright.options.WaitForSelectorState;

import java.util.List;

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

    public static void waitForAll(Page page, List<String> selectors) {
        for (String selector : selectors) {
            page.locator(selector)
                    .waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(3000));
        }
    }

    /**
     *Basically another version of {@code waitForAll()} method that handles waiting for the EditContactPage table fields.
     * This is required to wait until fields become populated with already defined values after Edit mode is enabled
     */
    public static void waitForAllEditable(Page page, List<String> selectors) {
        for (String selector : selectors) {
            try {
                Locator locator =  page.locator(selector);
                page.waitForCondition(() ->
                        locator.isVisible() && !locator.inputValue().isBlank());
            } catch (PlaywrightException e) {
                throw new AssertionError("field " + selector + " was not ready");
            }
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

    public static void waitForPageURL(Page page) {
        try {
            page.waitForURL(config().baseURL());
        } catch (PlaywrightException e) {
            throw new AssertionError("The page URL has not changed after the click. " +
                    "expected URL to be -> " + config().baseURL(), e);
        }
    }
}
