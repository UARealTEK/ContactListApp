package com.ContactList.UI.BaseClasses;

import com.microsoft.playwright.Page;

public class BaseComponent {

    protected Page page;

    protected BaseComponent(Page page) {
        this.page = page;
    }
}
