package com.ContactList.utils.helpers;

import com.ContactList.core.payloads.UserPayloads.UserBodyPayload;
import com.ContactList.core.responses.userResponses.UserResponse;
import com.ContactList.core.services.ContactsService;
import com.ContactList.core.services.UserService;
import com.ContactList.utils.dataManagement.DataGenerator;
import io.restassured.response.Response;

public class UserApiHelper {

    public static UserResponse createRandomUser() {
        UserBodyPayload payload = DataGenerator.getRandomUserPayload();
        Response response = new UserService().addUserRequest(payload);
        return response.getBody().as(UserResponse.class);
    }

    public static UserResponse createRandomUserWithOneContact() {
        UserResponse user = createRandomUser();
        new ContactsService().addContactRequest(user);
        return user;
    }
}
