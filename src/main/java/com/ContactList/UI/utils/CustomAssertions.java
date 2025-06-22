package com.ContactList.UI.utils;

import com.ContactList.UI.enums.AppEndpoints;
import com.ContactList.UI.pages.BasePage;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Arrays;
import java.util.stream.Collectors;

public class CustomAssertions {

    public static <T extends BasePage> boolean isCorrectURLOpened(T page, AppEndpoints... endpoints) {

        String fullPath = AppEndpoints.BASE_PATH.getEndpoint() + Arrays.stream(endpoints)
                .map(AppEndpoints::getEndpoint)
                .collect(Collectors.joining("/"));

        if (endpoints.length == 0) {
            return page.getDriver().getCurrentUrl().equals(AppEndpoints.BASE_PATH.getEndpoint());
        }

        try {
            page.getWait().until(ExpectedConditions.urlToBe(fullPath));
            String currentURL = page.getDriver().getCurrentUrl();
            System.out.println("full path is:" + fullPath);
            System.out.println("current path is " + page.getDriver().getCurrentUrl());
            return currentURL.equals(fullPath);
        } catch (TimeoutException e) {
            throw new AssertionError("Current URL: " + page.getDriver().getCurrentUrl() + "is not matched with expected URL: " + fullPath);
        }
    }
}
