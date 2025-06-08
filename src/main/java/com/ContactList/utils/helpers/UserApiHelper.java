package com.ContactList.utils.helpers;

import com.ContactList.core.payloads.UserPayload;
import com.ContactList.core.payloads.UserResponse;
import com.ContactList.core.services.UserService;
import com.ContactList.utils.dataManagement.DataGenerator;
import com.ContactList.utils.dataManagement.DataSerializer;
import io.restassured.response.Response;

public class UserApiHelper {

    public static UserResponse createRandomUser() {
        UserPayload payload = DataGenerator.getRandomUserPayload();
        Response response = new UserService().addUserRequest(payload);
        return response.getBody().as(UserResponse.class);
    }
}
