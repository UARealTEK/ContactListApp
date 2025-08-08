package com.ContactList.tests.composite;

import com.ContactList.API.core.payloads.ContactsPayloads.ContactsBodyPayload;
import com.ContactList.API.core.payloads.UserPayloads.UserBodyPayload;
import com.ContactList.API.utils.dataManagement.DataGenerator;
import com.ContactList.UI.BaseClasses.BaseTest;
import com.ContactList.UI.pages.ListPage.ListPage;
import com.ContactList.UI.utils.customUtils.assertions.CustomAPIAssertions;
import com.ContactList.UI.utils.customUtils.listeners.ResponseListeners;
import com.ContactList.UI.utils.endpoints.PageEndpoints;
import com.ContactList.demo.DTOs.ContactDTO;
import com.ContactList.demo.services.ContactService;
import com.ContactList.demo.utils.CustomDBAssertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Tag("composite")
@SpringBootTest(classes = com.ContactList.demo.Application.class)
public class CompositeTests extends BaseTest {

    @Autowired
    private ContactService contactService;

    //TODO: Think about performing DB save queries right INSIDE of the UI actions
    //TODO: Add DBContacts tests
    @Test
    public void checkSimpleContactAddingFlow() {
        SoftAssertions soft = new SoftAssertions();
        Long countBefore = contactService.getContactCount();
        UserBodyPayload user = DataGenerator.getRandomSafeUserPayload();
        ContactsBodyPayload payload = DataGenerator.getRandomContactPayload();
        ResponseListeners.clear();
        ResponseListeners.attachContactsListener(page);

        ListPage listPage = loginPage
                .openSignUpPage()
                //I'm not going to save this user into a DB during THIS test. This flow is present in the DB Tests
                .signUpUser(user)
                .openAddContactPage()
                .addContact(payload);

        contactService.saveContactToDB(ResponseListeners.getCapturedContactsResponse());
        Long countAfter = contactService.getContactCount();

        //Check that added contact on UI (obtained via .getLatestContactData()) matches with received '/contacts' response
        CustomAPIAssertions.assertAddedContact(soft,listPage.getTable().getLatestContactData());
        //Check that added contact on UI (obtained via .getLatestContactData()) matches with data for the latest added user in DB
        CustomDBAssertions.isLatestContactDTOEqualToUI(contactService,listPage.getTable().getLatestContactData());

        soft.assertThat(countBefore == countAfter -1).isTrue();
        soft.assertThat(listPage.getCurrentURL()).isEqualTo(PageEndpoints.getFullContactListURL());

        soft.assertAll();
    }

    @Test
    public void checkRichContactAddFlow() {
        SoftAssertions soft = new SoftAssertions();
        Long countBefore = contactService.getContactCount();
        UserBodyPayload user = DataGenerator.getRandomSafeUserPayload();
        ContactsBodyPayload contact = DataGenerator.getRandomRichContactPayload();
        ResponseListeners.clear();
        ResponseListeners.attachContactsListener(page);

        ListPage listPage = loginPage
                .openSignUpPage()
                .signUpUser(user)
                .openAddContactPage()
                .addContact(contact);

        contactService.saveContactToDB(ResponseListeners.getCapturedContactsResponse());
        CustomAPIAssertions.assertAddedContact(soft,contact);
        CustomDBAssertions.isLatestContactDTOEqualToUI(contactService,listPage.getTable().getLatestContactData());

        Long countAfter = contactService.getContactCount();
        soft.assertThat(countAfter == countBefore + 1).isTrue();
        soft.assertThat(listPage.getCurrentURL()).isEqualTo(PageEndpoints.getFullContactListURL());
        soft.assertAll();
    }

}
