package com.ContactList.tests.ui;

import com.ContactList.API.core.payloads.UserPayloads.UserBodyPayload;
import com.ContactList.API.utils.dataManagement.DataGenerator;
import com.ContactList.UI.pages.AddContactPage;
import com.ContactList.UI.pages.BaseTest;
import com.ContactList.UI.utils.endpoints.PageEndpoints;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

//TODO:
// - think about merging all necessary chain method calls into one (for proper SRP principle to work)
// - Question for Stanislav -> better to leave as it is? Or to indeed merge those chain calls into one method? how does it work?
public class ContactsTests extends BaseTest {

    @Test
    public void checkOpeningAddContactPage() {
        SoftAssertions soft = new SoftAssertions();
        UserBodyPayload payload = DataGenerator.getRandomUserPayload();

        AddContactPage page = loginPage
                .openSignUpPage()
                .signUpUser(payload)
                .openAddContactPage();

        soft.assertThat(page.getCurrentURL()).isEqualTo(PageEndpoints.getFullAddContactURL());
        soft.assertAll();
    }
}
