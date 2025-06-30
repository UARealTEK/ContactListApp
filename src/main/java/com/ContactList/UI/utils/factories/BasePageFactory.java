package com.ContactList.UI.utils.factories;

import com.ContactList.UI.pages.BasePage;
import com.microsoft.playwright.Page;

public class BasePageFactory {

    public static <T extends BasePage> T createInstance(Page page, Class<T> tClass) {
        try {
            BasePage basePage = tClass.getDeclaredConstructor().newInstance();
            basePage.configurePage(page);
            basePage.initComponents();

            return tClass.cast(basePage);
        } catch (Exception e) {
            throw new RuntimeException("Error while creating an instance");
        }
    }
}
