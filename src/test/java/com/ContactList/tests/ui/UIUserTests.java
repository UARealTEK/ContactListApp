package com.ContactList.tests.ui;

import com.ContactList.API.core.payloads.UserPayloads.UserBodyPayload;
import com.ContactList.API.utils.dataManagement.DataGenerator;
import com.ContactList.API.utils.helpers.UserApiHelper;
import com.ContactList.UI.BaseClasses.BaseTest;
import com.ContactList.UI.pages.ListPage.ListPage;
import com.ContactList.UI.pages.LoginPage.LoginPage;
import com.ContactList.UI.pages.SignUpPage.SignUpPage;
import com.ContactList.UI.utils.endpoints.PageEndpoints;
import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.ContactList.API.utils.dataManagement.DataGenerator.getRandomSafeUserPayload;
import static com.ContactList.UI.utils.Managers.ConfigurationManager.config;

@Tag("ui")
public class UIUserTests extends BaseTest {

    @Link(name = "Another test", url = "https://www.youtube.com/watch?v=vKVzRbsMnTQ")
    @Owner("Volodymyr")
    @Feature("UI AQA")
    @DisplayName("Opening a starting base page")
    @Story("UserUI")
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
     * in the following tests user credentials will be obtained using natural manual flow (see {@code checkUserSignUp()})
     */
    @Link(name = "Another test", url = "https://www.youtube.com/watch?v=vKVzRbsMnTQ")
    @Owner("Volodymyr")
    @Feature("UI AQA")
    @DisplayName("Logging in a user using already signed UP user data")
    @Story("UserUI")
    @Test
    public void checkUserLogin() {
        SoftAssertions soft = new SoftAssertions();
        UserBodyPayload payload = DataGenerator.getRandomSafeUserPayload();
        UserApiHelper.createSpecificUser(payload);

        ListPage page = loginPage.loginAsUser(payload);

        soft.assertThat(page.getCurrentURL()).isEqualTo(PageEndpoints.getFullContactListURL());

        soft.assertAll();
    }

    @Link(name = "Another test", url = "https://www.youtube.com/watch?v=vKVzRbsMnTQ")
    @Owner("Volodymyr")
    @Feature("UI AQA")
    @DisplayName("Opening a SignUP page")
    @Story("UserUI")
    @Test
    public void checkSignUpPageOpening() {
        SoftAssertions soft = new SoftAssertions();
        SignUpPage page = loginPage.openSignUpPage();

        soft.assertThat(page.getCurrentURL()).isEqualTo(PageEndpoints.getFullSignUpURL());

        soft.assertAll();
    }

    @Link(name = "Another test", url = "https://www.youtube.com/watch?v=vKVzRbsMnTQ")
    @Owner("Volodymyr")
    @Feature("UI AQA")
    @DisplayName("Full SignUp user flow")
    @Story("UserUI")
    @RepeatedTest(10)
    public void checkUserSignUp() {
        SoftAssertions soft = new SoftAssertions();
        UserBodyPayload payload = getRandomSafeUserPayload();

        ListPage page = loginPage.openSignUpPage().signUpUser(payload);

        soft.assertThat(page.getCurrentURL()).isEqualTo(PageEndpoints.getFullContactListURL());

        soft.assertAll();
    }

    @Link(name = "Another test", url = "https://www.youtube.com/watch?v=vKVzRbsMnTQ")
    @Owner("Volodymyr")
    @Feature("UI AQA")
    @DisplayName("Canceling out of SignUp process")
    @Story("UserUI")
    @Test
    public void checkCancelSignUpProcess() {
        SoftAssertions soft = new SoftAssertions();
        LoginPage page = loginPage.openSignUpPage().cancelSignUp();

        soft.assertThat(page.getCurrentURL()).isEqualTo(PageEndpoints.getFullLoginURL());

        soft.assertAll();
    }
}
