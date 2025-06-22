package com.ContactList.tests.ui;

import com.ContactList.API.utils.dataManagement.DataGenerator;
import com.ContactList.UI.enums.AppEndpoints;
import com.ContactList.UI.pages.BasePage;
import com.ContactList.UI.pages.LoginPage;
import com.ContactList.UI.pages.UserPage;
import com.ContactList.UI.testSupport.BaseTest;
import com.ContactList.UI.utils.CustomAssertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

public class MainTests extends BaseTest {

    @Test
    public void checkOpeningBasePage() {
        SoftAssertions soft = new SoftAssertions();
        LoginPage page = new BasePage().navigateTo(LoginPage::new);

        soft.assertThat(CustomAssertions.isCorrectURLOpened(page)).isTrue();
        soft.assertAll();
    }

    @RepeatedTest(3)
    public void checkUserLogin() throws InterruptedException {
        SoftAssertions soft = new SoftAssertions();
        UserPage page = new BasePage().navigateTo(LoginPage::new)
                .loginUser(DataGenerator.getUserLoginPayload());

        soft.assertThat(CustomAssertions.isCorrectURLOpened(page, AppEndpoints.USER_PAGE)).isTrue();
        soft.assertAll();
    }

}
