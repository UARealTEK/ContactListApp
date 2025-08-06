package com.ContactList.tests.composite;

import com.ContactList.API.core.payloads.ContactsPayloads.ContactsBodyPayload;
import com.ContactList.API.core.payloads.UserPayloads.UserBodyPayload;
import com.ContactList.API.utils.dataManagement.DataGenerator;
import com.ContactList.UI.BaseClasses.BaseTest;
import com.ContactList.UI.pages.ListPage.ListPage;
import com.ContactList.UI.pages.ListPage.utils.ListPageEndpoints;
import com.ContactList.UI.utils.customUtils.listeners.ResponseListeners;
import com.ContactList.UI.utils.customUtils.serializers.JsonUtils;
import com.ContactList.UI.utils.customUtils.waitUtils.WaitUtils;
import com.ContactList.UI.utils.endpoints.PageEndpoints;
import com.fasterxml.jackson.core.type.TypeReference;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;

@Tag("composite")
public class CompositeTests extends BaseTest {

    //TODO: Add DTO class for Contacts. Check that after adding -> user is added in the Database as well
    // TODO: think about how contacts are placed in the "contacts" endpoint response? I don't know what is the rule.
    //  Which are placed at the top? Same as in the table?
    //TODO: read about ObjectMappers
    //TODO: read about Object.equals()
    @Test
    public void checkSimpleContactAddingFlow() {
        SoftAssertions soft = new SoftAssertions();
        UserBodyPayload user = DataGenerator.getRandomSafeUserPayload();
        ContactsBodyPayload payload = DataGenerator.getRandomContactPayload();
        ResponseListeners.clear();
        ResponseListeners.attachContactsListener(page);

        ListPage listPage = loginPage
                .openSignUpPage()
                .signUpUser(user)
                .openAddContactPage()
                .addContact(payload);

        try {
            List<ContactsBodyPayload> payloads = JsonUtils
                    .parseList(ResponseListeners.getCapturedContactsResponse().text(), new TypeReference<>(){});
            System.out.println(payloads.getFirst());
            System.out.println(payload);
            soft.assertThat(payloads.getFirst().equals(payload)).isTrue();
        } catch (RuntimeException e) {
            throw new AssertionError("The payload was empty. Contacts APi call was probably not caught");
        }

        soft.assertThat(listPage.getCurrentURL()).isEqualTo(PageEndpoints.getFullContactListURL());

        soft.assertAll();
    }

}
