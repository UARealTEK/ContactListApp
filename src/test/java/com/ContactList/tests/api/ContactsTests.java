package com.ContactList.tests.api;

import com.ContactList.core.payloads.ContactsPayloads.ContactsBodyPayload;
import com.ContactList.core.responses.contactsResponses.ContactResponse;
import com.ContactList.core.responses.userResponses.UserResponse;
import com.ContactList.core.services.ContactsService;
import com.ContactList.utils.dataManagement.DataGenerator;
import com.ContactList.utils.helpers.ContactApiHelper;
import com.ContactList.utils.helpers.UserApiHelper;
import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * FYI:
 * 1. Creation of a user (needed to obtain a Token. Token is a shared value which is needed for further Requests)
 * <p>
 * 2. Creation of Contact (done using a shared Token. Creation of user is required to perform different GET method requests for /contacts endpoint)
 * <p>
 * 3. Assertions. For current implementation I'm asserting only for positive Request execution (status codes 200 / 201)
 */

@Tag("api")
@Tag("contacts")
public class ContactsTests {

    @RepeatedTest(20)
    public void checkAddContact() {
        SoftAssertions soft = new SoftAssertions();
        UserResponse user = UserApiHelper.createRandomUser();
        Response response = new ContactsService().addContactRequest(user);

        soft.assertThat(response.getStatusCode()).isEqualTo(201);

        soft.assertAll();
    }

    @RepeatedTest(3)
    public void checkAddContactWithStreet() {
        SoftAssertions soft = new SoftAssertions();
        UserResponse user = UserApiHelper.createRandomUser();
        Response response = new ContactsService().addRichContactRequest(user);

        soft.assertThat(response.getStatusCode()).isEqualTo(201);

        soft.assertAll();
    }

    @Test
    public void checkGetContactList() {
        SoftAssertions soft = new SoftAssertions();
        ContactsService service = new ContactsService();

        UserResponse user = UserApiHelper.createRandomUser();
        service.addContactRequest(user).as(ContactResponse.class);
        Response response = service.getAllContactsRequest(user);

        soft.assertThat(response.getStatusCode()).isEqualTo(200);

        soft.assertAll();
    }

    @Test
    public void checkGetRandomContact() {
        SoftAssertions soft = new SoftAssertions();
        ContactsService service = new ContactsService();

        UserResponse user = UserApiHelper.createRandomUser();
        ContactResponse contact = service.addContactRequest(user).as(ContactResponse.class);
        Response response = service.getContactRequest(user,contact);

        soft.assertThat(response.getStatusCode()).isEqualTo(200);

        soft.assertAll();
    }

    @Test
    public void checkUpdateRandomContact() {
        SoftAssertions soft = new SoftAssertions();
        ContactsService service = new ContactsService();

        UserResponse user = UserApiHelper.createRandomUser();
        ContactResponse contact = service.addContactRequest(user).as(ContactResponse.class);
        ContactsBodyPayload payload = DataGenerator.getRandomContactPayload();

        Response response = service.putContactRequest(user,payload,contact);

        soft.assertThat(response.getStatusCode()).isEqualTo(200);

        soft.assertAll();
    }

    @Test
    public void checkPartiallyUpdateRandomContact() {
        SoftAssertions soft = new SoftAssertions();
        ContactsService service = new ContactsService();

        UserResponse user = UserApiHelper.createRandomUserWithOneContact();
        ContactResponse contact = ContactApiHelper.getAnyContact(user);
        ContactsBodyPayload payload = DataGenerator.getRandomContactPayloadEntry();

        Response response = service.patchContactRequest(user,payload,contact);

        soft.assertThat(response.getStatusCode()).isEqualTo(200);

        soft.assertAll();
    }

    @Test
    public void checkDeleteRandomContact() {
        SoftAssertions soft = new SoftAssertions();
        UserResponse user = UserApiHelper.createRandomUserWithOneContact();
        ContactResponse contact = ContactApiHelper.getAnyContact(user);

        Response response = new ContactsService().deleteContactRequest(user,contact);

        soft.assertThat(response.getStatusCode()).isEqualTo(200);

        soft.assertAll();
    }
}
