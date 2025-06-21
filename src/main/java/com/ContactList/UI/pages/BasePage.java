package com.ContactList.UI.pages;

import com.ContactList.UI.driver.DriverManager;
import com.ContactList.UI.enums.AppEndpoints;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    @Getter
    private final WebDriver driver;
    @Getter
    private final WebDriverWait wait;

    public BasePage() {
        this.driver = DriverManager.getDriver();
        this.wait = DriverManager.getWait();
    }

    protected LoginPage navigateToBaseURL() {
        getDriver().get(AppEndpoints.BASE_PATH.getEndpoint());
        return new LoginPage();
    }


}
