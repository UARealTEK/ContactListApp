package com.ContactList.tests.composite;

import com.ContactList.API.core.payloads.ContactsPayloads.ContactsBodyPayload;
import com.ContactList.API.core.payloads.UserPayloads.UserBodyPayload;
import com.ContactList.API.utils.dataManagement.DataGenerator;
import com.ContactList.UI.BaseClasses.BaseTest;
import com.ContactList.UI.pages.ListPage.ListPage;
import com.ContactList.UI.utils.endpoints.PageEndpoints;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("composite")
public class CompositeTests extends BaseTest {

    @Test
    public void checkSimpleContactAddingFlow() {
        SoftAssertions soft = new SoftAssertions();
        UserBodyPayload user = DataGenerator.getRandomSafeUserPayload();
        ContactsBodyPayload payload = DataGenerator.getRandomContactPayload();

        ListPage listPage = loginPage
                .openSignUpPage()
                .signUpUser(user)
                .openAddContactPage()
                .addContact(payload);

        soft.assertThat(listPage.getCurrentURL()).isEqualTo(PageEndpoints.getFullContactListURL());

        soft.assertAll();
    }

}
