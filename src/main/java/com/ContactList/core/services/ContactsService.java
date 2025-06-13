package com.ContactList.core.services;

import com.ContactList.config.enums.headers.Headers;
import com.ContactList.core.payloads.ContactsPayloads.ContactsBodyPayload;
import com.ContactList.core.responses.contactsResponses.ContactResponse;
import com.ContactList.core.responses.userResponses.UserResponse;
import com.ContactList.utils.dataManagement.DataGenerator;
import com.ContactList.utils.helpers.UserApiHelper;
import io.restassured.response.Response;

import java.util.Map;

public class ContactsService extends BaseService {

    private static final String BASE_PATH = "contacts";

    public Response addContactRequest() {
        UserResponse user = UserApiHelper.createRandomUser();
        ContactsBodyPayload payload = DataGenerator.getRandomContactPayload();
        System.out.println(payload);
        return postRequest(payload, Map.of(Headers.AUTHORIZATION.getHeader(), user.getToken()), BASE_PATH);
    }
}
