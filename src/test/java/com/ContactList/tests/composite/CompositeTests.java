package com.ContactList.tests.composite;

import com.ContactList.API.core.payloads.ContactsPayloads.ContactsBodyPayload;
import com.ContactList.API.core.payloads.UserPayloads.UserBodyPayload;
import com.ContactList.API.core.responses.userResponses.UserResponse;
import com.ContactList.API.core.services.ContactsService;
import com.ContactList.API.core.services.UserService;
import com.ContactList.API.utils.dataManagement.DataGenerator;
import com.ContactList.UI.BaseClasses.BaseTest;
import com.ContactList.UI.pages.ListPage.ListPage;
import com.ContactList.UI.utils.customUtils.assertions.CustomAPIAssertions;
import com.ContactList.UI.utils.customUtils.listeners.ResponseListeners;
import com.ContactList.UI.utils.endpoints.PageEndpoints;
import com.ContactList.demo.services.ContactService;
import com.ContactList.demo.utils.CustomDBAssertions;
import io.restassured.response.Response;
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

    //TODO: Add DBContacts tests
    //TODO: add Generic check to verify API ('contacts') and UI (Full Table data) -> check order
    //TODO: use the approach when we first do some UI action. THEN -> perform API request ->
    //  compare data on UI with received response from API
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

    @Test
    public void checkAPIConsistencyWithUI() {
        SoftAssertions soft = new SoftAssertions();
        ResponseListeners.clear();
        ResponseListeners.attachContactsListener(page);

        UserBodyPayload userBody = DataGenerator.getRandomSafeUserPayload(); // User Body Payload
        Response userResponse = new UserService().addUserRequest(userBody); // performed API request to create a User
        UserResponse user = userResponse.as(UserResponse.class); // fully created User
        soft.assertThat(userResponse.getStatusCode()).isEqualTo(201); // assert that User was successfully created

        ContactsBodyPayload payload = DataGenerator.getRandomContactPayload(); // Contact Body payload
        Response contactResponse = new ContactsService().addSpecificContactRequest(user,payload); // performed API request to create a contact
        soft.assertThat(contactResponse.getStatusCode()).isEqualTo(201); // assert that Contact was successfully created

        //Manual UI login using created User data
        ListPage listPage = loginPage
                .loginAsUser(userBody);

        //Assert that latest (the only one) added contact is equal to the received Response from the '/contacts' API request
        CustomAPIAssertions.assertAddedContact(soft,listPage.getTable().getLatestContactData()); // parse '/contacts' API request

        //Assert that latest (The only one) added contact is equal to the one which was created using API request
        soft.assertThat(listPage.getTable().getLatestContactData().equals(payload)).isTrue();

        soft.assertAll();
    }

}
