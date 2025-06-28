package com.ContactList.tests.ui;

import com.ContactList.API.core.payloads.UserPayloads.UserBodyPayload;
import com.ContactList.API.core.responses.userResponses.UserResponse;
import com.ContactList.API.utils.dataManagement.DataGenerator;
import com.ContactList.API.utils.helpers.UserApiHelper;
import com.ContactList.UI.pages.BaseTest;
import com.ContactList.UI.pages.ListPage;
import com.ContactList.UI.pages.LoginPage;
import com.ContactList.UI.pages.SignUpPage;
import com.ContactList.UI.utils.endpoints.PageEndpoints;
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

    @Test
    public void checkUserLogin() {
        SoftAssertions soft = new SoftAssertions();
        UserBodyPayload payload = DataGenerator.getRandomUserPayload();
        UserApiHelper.createSpecificUser(payload);

        ListPage page = loginPage.loginAsUser(payload);

        soft.assertThat(page.getCurrentURL()).isEqualTo(PageEndpoints.getFullContactListURL());

        soft.assertAll();
    }

    @Test
    public void checkSignUpPageOpening() {
        SoftAssertions soft = new SoftAssertions();
        SignUpPage page = loginPage.openSignUpPage();

        soft.assertThat(page.getCurrentURL()).isEqualTo(PageEndpoints.getFullSignUpURL());

        soft.assertAll();
    }
}
