package com.ContactList.UI.BaseClasses;

import com.microsoft.playwright.Page;
import lombok.Getter;

import static com.ContactList.UI.utils.Managers.ConfigurationManager.config;
@Getter
public abstract class BasePage {

    protected Page page;

    public void configurePage(Page page) {
        this.page = page;
        page.setDefaultTimeout(config().timeout());
    }

    public abstract void initComponents();

    public String getCurrentURL() {
        return page.url();
    }
}
