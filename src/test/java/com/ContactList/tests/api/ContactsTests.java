package com.ContactList.tests.api;

import com.ContactList.core.services.ContactsService;
import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

public class ContactsTests {

    //TODO: work on data generation. Sometimes the test fails due to invalid field values
    @Test
    public void checkAddContact() {
        SoftAssertions soft = new SoftAssertions();
        Response response = new ContactsService().addContactRequest();

        System.out.println(response.getBody().asPrettyString());

        soft.assertThat(response.getStatusCode()).isEqualTo(201);

        soft.assertAll();
    }
}
