package com.ContactList.UI.utils;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Playwright;

import static com.ContactList.UI.config.ConfigurationManager.config;

public class BrowserManager {

    public static Browser initBrowser(Playwright playwright) {
        return BrowserFactory.valueOf(config().browser().toUpperCase()).createBrowser(playwright);
    }
}
