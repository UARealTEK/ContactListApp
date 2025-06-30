package com.ContactList.tests.ui;

import com.ContactList.API.core.payloads.UserPayloads.UserBodyPayload;
import com.ContactList.API.utils.dataManagement.DataGenerator;
import com.ContactList.API.utils.helpers.UserApiHelper;
import com.ContactList.UI.BaseClasses.BaseTest;
import com.ContactList.UI.pages.ListPage.ListPage;
import com.ContactList.UI.pages.LoginPage.LoginPage;
import com.ContactList.UI.pages.SignUpPage.SignUpPage;
import com.ContactList.UI.utils.endpoints.PageEndpoints;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

import static com.ContactList.UI.utils.Managers.ConfigurationManager.config;

public class UserTests extends BaseTest {

    @Test
    public void checkBasePageOpening() {
        SoftAssertions soft = new SoftAssertions();
        LoginPage page = loginPage.openLoginPage();

        soft.assertThat(page.getCurrentURL()).isEqualTo(config().baseURL());

        soft.assertAll();
    }

    /**
     * In this specific case -> signUp was done using separate API calls (methods)
     * Therefore The login credentials Payload for the user was received using those specific API calls
     *
     * in the following tests user credentials will be obtained using natural manual flow (see {@code checkUserSignUp()})
     */
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

    @Test
    public void checkUserSignUp() {
        SoftAssertions soft = new SoftAssertions();
        UserBodyPayload payload = DataGenerator.getRandomUserPayload();

        ListPage page = loginPage.openSignUpPage().signUpUser(payload);

        soft.assertThat(page.getCurrentURL()).isEqualTo(PageEndpoints.getFullContactListURL());

        soft.assertAll();
    }

    @Test
    public void checkCancelSignUpProcess() {
        SoftAssertions soft = new SoftAssertions();
        LoginPage page = loginPage.openSignUpPage().cancelSignUp();

        soft.assertThat(page.getCurrentURL()).isEqualTo(PageEndpoints.getFullLoginURL());

        soft.assertAll();
    }
}
