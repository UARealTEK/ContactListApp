package com.ContactList.UI.utils;

import com.ContactList.API.utils.dataManagement.DataGenerator;
import com.ContactList.API.utils.helpers.UserApiHelper;
import com.ContactList.UI.pages.BasePage;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import java.lang.reflect.InvocationTargetException;

public class BasePageFactory {

    public static <T extends BasePage> T createInstance(Page page, Class<T> tClass) {
        try {
            BasePage basePage = tClass.getDeclaredConstructor().newInstance();
            basePage.configurePage(page, UserApiHelper.createRandomUser());
            basePage.initComponents();

            return tClass.cast(basePage);
        } catch (Exception e) {
            throw new RuntimeException("Error while creating an instance");
        }
    }
}
