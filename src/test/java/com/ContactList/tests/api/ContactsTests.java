package com.ContactList.tests.api;

import com.ContactList.core.payloads.ContactsPayloads.ContactsBodyPayload;
import com.ContactList.core.responses.contactsResponses.ContactResponse;
import com.ContactList.core.responses.userResponses.UserResponse;
import com.ContactList.core.services.ContactsService;
import com.ContactList.utils.dataManagement.DataGenerator;
import com.ContactList.utils.helpers.UserApiHelper;
import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * FYI:
 * 1. Creation of a user (needed to obtain a Token. Token is a shared value which is needed for further Requests)
 * <p>
 * 2. Creation of Contact (done using a shared Token. Creation of user is required to perform different GET method requests for /contacts endpoint)
 * <p>
 * 3. Assertions. For current implementation I'm asserting only for positive Request execution (status codes 200 / 201)
 */

public class ContactsTests {

    @RepeatedTest(3)
    public void checkAddContact() {
        SoftAssertions soft = new SoftAssertions();
        UserResponse user = UserApiHelper.createRandomUser();
        Response response = new ContactsService().addContactRequest(user);

        soft.assertThat(response.getStatusCode()).isEqualTo(201);

        soft.assertAll();
    }

    //TODO:
    // fix it. receiving 400
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

        UserResponse user = UserApiHelper.createRandomUser();
        ContactsBodyPayload payload = DataGenerator.getRandomContactPayloadEntry();
        ContactResponse contact = service.addContactRequest(user).as(ContactResponse.class);

        System.out.println("Payload -> " + payload);
        System.out.println("Contact -> " + contact);

        Response response = service.patchContactRequest(user,payload,contact);

        System.out.println(response.as(ContactResponse.class));

        soft.assertThat(response.getStatusCode()).isEqualTo(200);

        soft.assertAll();
    }

    //TODO:
    // improve logic. Test is doing too much -> probably violates SRP
    // Possible improvements :
    //    UserResponse user = UserApiHelper.createRandomUserWithOneContact();
    //    ContactResponse contact = ContactApiHelper.getAnyContact(user);
    @Test
    public void checkDeleteRandomContact() {
        SoftAssertions soft = new SoftAssertions();
        ContactsService service = new ContactsService();
        ThreadLocalRandom random = ThreadLocalRandom.current();

        UserResponse user = UserApiHelper.createRandomUser();
        service.addContactRequest(user).as(ContactResponse.class);
        List<ContactResponse> contacts = service.getAllContacts(user);

        Response response = service.deleteContactRequest(user,contacts.get(random.nextInt(contacts.size())));

        soft.assertThat(response.getStatusCode()).isEqualTo(200);

        soft.assertAll();
    }
}
