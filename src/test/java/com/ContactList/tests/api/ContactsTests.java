package com.ContactList.tests.api;

import com.ContactList.core.responses.contactsResponses.ContactResponse;
import com.ContactList.core.services.ContactsService;
import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

public class ContactsTests {

    //TODO: work on data generation. Sometimes the test fails due to invalid field values
    @RepeatedTest(3)
    public void checkAddContact() {
        SoftAssertions soft = new SoftAssertions();
        Response response = new ContactsService().addContactRequest();

        System.out.println(response.getBody().asPrettyString());

        soft.assertThat(response.getStatusCode()).isEqualTo(201);

        soft.assertAll();
    }

    @RepeatedTest(3)
    public void checkAddContactWithStreet() {
        SoftAssertions soft = new SoftAssertions();
        Response response = new ContactsService().addRichContactRequest();

        soft.assertThat(response.getStatusCode()).isEqualTo(201);

        System.out.println(response.getBody().as(ContactResponse.class));

        soft.assertAll();
    }
}
