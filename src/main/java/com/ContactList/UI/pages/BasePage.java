package com.ContactList.UI.pages;

import com.ContactList.API.core.payloads.UserPayloads.UserLoginPayload;
import com.ContactList.API.core.responses.userResponses.UserResponse;
import com.ContactList.API.utils.dataManagement.DataGenerator;
import com.microsoft.playwright.Page;
import lombok.Getter;

import static com.ContactList.UI.config.ConfigurationManager.config;
@Getter
public abstract class BasePage {

    protected Page page;
    protected UserResponse user;

    public void configurePage(Page page, UserResponse user) {
        this.page = page;
        this.user = user;
        page.setDefaultTimeout(config().timeout());
    }

    public abstract void initComponents();

    public String getCurrentURL() {
        return page.url();
    }
}
