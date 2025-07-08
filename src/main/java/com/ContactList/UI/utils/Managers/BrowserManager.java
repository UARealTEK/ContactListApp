package com.ContactList.UI.utils.Managers;

import com.ContactList.UI.configurations.BrowsersConfiguration;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Playwright;

import static com.ContactList.UI.utils.Managers.ConfigurationManager.config;

public class BrowserManager {

    public static Browser initBrowser(Playwright playwright) {
        return BrowsersConfiguration.valueOf(config().browser().toUpperCase()).createBrowser(playwright);
    }
}
