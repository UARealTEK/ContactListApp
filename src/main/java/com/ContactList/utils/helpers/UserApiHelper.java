package com.ContactList.utils.helpers;

import com.ContactList.core.payloads.UserBodyPayload;
import com.ContactList.core.payloads.UserLoginPayload;
import com.ContactList.core.payloads.UserResponse;
import com.ContactList.core.services.UserService;
import com.ContactList.utils.dataManagement.DataGenerator;
import io.restassured.response.Response;

public class UserApiHelper {

    public static UserResponse createRandomUser() {
        UserBodyPayload payload = DataGenerator.getRandomUserPayload();
        Response response = new UserService().addUserRequest(payload);
        return response.getBody().as(UserResponse.class);
    }

    public static UserLoginPayload getUserLoginPayload() {
        UserBodyPayload payload = DataGenerator.getRandomUserPayload();
        Response response = new UserService().addUserRequest(payload);

        if (response.getStatusCode() != 201) {
            throw new RuntimeException(String.format("failed to create a user. Status code is -> %s", response.getStatusCode()));
        }

        return new UserLoginPayload(payload.getEmail(),payload.getPassword());
    }
}
