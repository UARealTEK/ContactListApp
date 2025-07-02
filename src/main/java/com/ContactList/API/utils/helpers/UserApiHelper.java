package com.ContactList.API.utils.helpers;

import com.ContactList.API.core.payloads.UserPayloads.UserBodyPayload;
import com.ContactList.API.core.responses.userResponses.UserResponse;
import com.ContactList.API.core.services.ContactsService;
import com.ContactList.API.core.services.UserService;
import com.ContactList.API.utils.dataManagement.DataGenerator;
import io.restassured.response.Response;

public class UserApiHelper {

    public static UserResponse createRandomUser() {
        int attempts = 10;
        int currentAttempts = 0;

        while (currentAttempts < attempts) {
            try {
                UserBodyPayload payload = DataGenerator.getRandomUserPayload();
                Response response = new UserService().addUserRequest(payload);

                if (response.getStatusCode() == 201) {
                    return response.getBody().as(UserResponse.class);
                } else System.out.println("attempt " + currentAttempts + " has failed");
                System.out.println("response is " + response.getBody().asPrettyString());
            } catch (Exception e) {
                System.out.printf("Attempt %d: Exception occurred while creating user: %s%n",
                        attempts + 1, e.getMessage());
            }

            currentAttempts++;
        }

        throw new RuntimeException("Failed to create a user after all attempts");
    }

    public static UserResponse createSpecificUser(UserBodyPayload payload) {
        int attempts = 10;
        int currentAttempts = 0;

        while (currentAttempts < attempts) {
            try {
                Response response = new UserService().addUserRequest(payload);

                if (response.getStatusCode() == 201) {
                    return response.getBody().as(UserResponse.class);
                } else System.out.println("attempt " + currentAttempts + " has failed");
                System.out.println("response is " + response.getBody().asPrettyString());
            } catch (Exception e) {
                System.out.printf("Attempt %d: Exception occurred while creating user: %s%n",
                        attempts + 1, e.getMessage());
            }

            currentAttempts++;
        }

        throw new RuntimeException("Failed to create a user after all attempts");
    }

    public static UserResponse createRandomUserWithOneContact() {
        UserResponse user = createRandomUser();
        new ContactsService().addContactRequest(user);
        return user;
    }


}
