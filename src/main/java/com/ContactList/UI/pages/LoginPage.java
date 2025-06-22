package com.ContactList.UI.pages;

import com.ContactList.API.core.payloads.UserPayloads.UserLoginPayload;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Getter
public class LoginPage extends BasePage{

    public LoginPage() {
        super();
    }

    private static final By emailField = By.id("email");
    private static final By passwordField = By.id("password");
    private static final By submitButton = By.id("submit");

    protected WebElement getEmailField() {
        return getDriver().findElement(emailField);
    }

    protected WebElement getPasswordField() {
        return getDriver().findElement(passwordField);
    }

    protected WebElement getSubmitButton() {
        return getDriver().findElement(submitButton);
    }

    private LoginPage insertUserData(UserLoginPayload payload) {
        new Actions(getDriver())
                .scrollToElement(getEmailField()).click(getEmailField()).sendKeys(payload.getEmail())
                .scrollToElement(getPasswordField()).click(getPasswordField()).sendKeys(payload.getPassword())
                .build().perform();
        return this;
    }

    private UserPage clickSubmitButton() {
        new Actions(getDriver())
                .scrollToElement(getSubmitButton()).click(getSubmitButton())
                .build().perform();
        return new UserPage();
    }

    public UserPage loginUser(UserLoginPayload payload) {
        return insertUserData(payload).clickSubmitButton();
    }
}
