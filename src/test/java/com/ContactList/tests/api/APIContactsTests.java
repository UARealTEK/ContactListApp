package com.ContactList.tests.api;

import com.ContactList.API.core.payloads.ContactsPayloads.ContactsBodyPayload;
import com.ContactList.API.core.responses.contactsResponses.ContactResponse;
import com.ContactList.API.core.responses.userResponses.UserResponse;
import com.ContactList.API.core.services.ContactsService;
import com.ContactList.API.utils.dataManagement.DataGenerator;
import com.ContactList.API.utils.helpers.ContactApiHelper;
import com.ContactList.API.utils.helpers.UserApiHelper;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import io.qameta.allure.*;

/**
 * FYI:
 * 1. Creation of a user (needed to obtain a Token. Token is a shared value which is needed for further Requests)
 * <p>
 * 2. Creation of Contact (done using a shared Token. Creation of user is required to perform different GET method requests for /contacts endpoint)
 * <p>
 * 3. Assertions. For current implementation I'm asserting only for positive Request execution (status codes 200 / 201)
 */

@Tag("api")
public class APIContactsTests {


    @Link(name = "Internal documentation", url = "https://www.youtube.com/watch?v=xvFZjo5PgG0")
    @Owner("Volodymyr")
    @Feature("ContactsAPI")
    @DisplayName("Request for adding a Contact")
    @Description("adding a Contact")
    @Story("addContact request")
    @RepeatedTest(20)
    public void checkAddContact() {
        SoftAssertions soft = new SoftAssertions();
        UserResponse user = UserApiHelper.createRandomUser();
        Response response = new ContactsService().addContactRequest(user);

        soft.assertThat(response.getStatusCode()).isEqualTo(201);

        soft.assertAll();
    }

    @Link(name = "Internal documentation", url = "https://www.youtube.com/watch?v=xvFZjo5PgG0")
    @Owner("Volodymyr")
    @Feature("ContactsAPI")
    @DisplayName("Request for adding a Contact that contains 'street' field in its payload")
    @Description("adding a Contact that contains 'street' field in its payload")
    @Story("addContact request")
    @RepeatedTest(3)
    public void checkAddContactWithStreet() {
        SoftAssertions soft = new SoftAssertions();
        UserResponse user = UserApiHelper.createRandomUser();
        Response response = new ContactsService().addRichContactRequest(user);

        soft.assertThat(response.getStatusCode()).isEqualTo(201);

        soft.assertAll();
    }

    @Link(name = "Internal documentation", url = "https://www.youtube.com/watch?v=xvFZjo5PgG0")
    @Owner("Volodymyr")
    @Feature("ContactsAPI")
    @DisplayName("Request for getting ALL Contacts")
    @Description("getting ALL Contacts")
    @Story("getAllContact request")
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

    @Link(name = "Internal documentation", url = "https://www.youtube.com/watch?v=xvFZjo5PgG0")
    @Owner("Volodymyr")
    @Feature("ContactsAPI")
    @DisplayName("Request for getting a random Contact")
    @Description("getting a random Contact")
    @Story("getContact request")
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

    @Link(name = "Internal documentation", url = "https://www.youtube.com/watch?v=xvFZjo5PgG0")
    @Owner("Volodymyr")
    @Feature("ContactsAPI")
    @DisplayName("Request for editing a Contact")
    @Description("editing a Contact")
    @Story("putContact request")
    @RepeatedTest(10)
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

    @Link(name = "Internal documentation", url = "https://www.youtube.com/watch?v=xvFZjo5PgG0")
    @Owner("Volodymyr")
    @Feature("ContactsAPI")
    @DisplayName("Request for partially editing a Contact")
    @Description("partially editing a Contact")
    @Story("patchContact request")
    @RepeatedTest(100)
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

    @Link(name = "Internal documentation", url = "https://www.youtube.com/watch?v=xvFZjo5PgG0")
    @Owner("Volodymyr")
    @Feature("ContactsAPI")
    @DisplayName("Request for removing a Contact")
    @Description("removing a Contact")
    @Story("deleteContact request")
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
