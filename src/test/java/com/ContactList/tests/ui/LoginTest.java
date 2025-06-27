package com.ContactList.tests.ui;

import com.ContactList.UI.pages.BaseTest;
import com.ContactList.UI.pages.LoginPage;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

import static com.ContactList.UI.config.ConfigurationManager.config;

public class LoginTest extends BaseTest {

    @Test
    public void checkBasePageOpening() {
        SoftAssertions soft = new SoftAssertions();
        LoginPage page = loginPage.openLoginPage();

        soft.assertThat(page.getCurrentURL()).isEqualTo(config().baseURL());

        soft.assertAll();
    }
}
