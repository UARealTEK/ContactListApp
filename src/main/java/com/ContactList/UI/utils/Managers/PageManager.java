package com.ContactList.UI.utils.Managers;

import com.ContactList.UI.BaseClasses.BasePage;
import com.microsoft.playwright.Page;

public class PageManager {

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
