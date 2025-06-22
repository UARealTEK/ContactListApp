package com.ContactList.UI.pages;

import com.ContactList.UI.driver.DriverManager;
import com.ContactList.UI.enums.AppEndpoints;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class BasePage {

    @Getter
    private final WebDriver driver;
    @Getter
    private final WebDriverWait wait;

    public BasePage() {
        this.driver = DriverManager.getDriver();
        this.wait = DriverManager.getWait();
    }

    public <T extends BasePage> T navigateTo(Supplier<T> supplier, AppEndpoints... endpoint) {
        if (endpoint.length == 0) {
            getDriver().get(AppEndpoints.BASE_PATH.getEndpoint());
        } else {
            String url = Arrays.stream(endpoint)
                    .map(AppEndpoints::getEndpoint)
                    .collect(Collectors.joining("/"));

            getDriver().get(AppEndpoints.BASE_PATH.getEndpoint() + "/" + url);
        }

        return supplier.get();
    }


}
