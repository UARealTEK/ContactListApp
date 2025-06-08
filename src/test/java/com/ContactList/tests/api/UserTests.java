package com.ContactList.tests.api;

import com.ContactList.core.payloads.UserPayload;
import com.ContactList.core.payloads.UserResponse;
import com.ContactList.core.services.UserService;
import com.ContactList.utils.dataManagement.DataGenerator;
import com.ContactList.utils.dataManagement.DataSerializer;
import com.ContactList.utils.helpers.UserApiHelper;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.assertj.core.api.SoftAssertions;

public class UserTests {

    @Test
    public void checkAddUser() {
        SoftAssertions soft = new SoftAssertions();
        Response response = new UserService().addUserRequest(DataGenerator.getRandomUserPayload());

        soft.assertThat(response.getStatusCode()).isEqualTo(201);

        soft.assertAll();
    }

    @Test
    public void checkGetUser() {
        SoftAssertions soft = new SoftAssertions();
        Response response = new UserService().getUserProfile(UserApiHelper.createRandomUser().getToken());

        soft.assertThat(response.getStatusCode()).isEqualTo(200);

        System.out.println(response.getBody().asPrettyString());
        soft.assertAll();
    }

    @Test
    public void checkUpdateUser() {
        SoftAssertions soft = new SoftAssertions();
        UserResponse user = UserApiHelper.createRandomUser();
        Response patch = new UserService().patchUserRequest(DataGenerator.getRandomUserPayload(), user.getToken());

        soft.assertThat(user.getUser().getId()).isEqualTo(patch.as(UserResponse.User.class).getId());
        soft.assertThat(patch.getStatusCode()).isEqualTo(200);

        soft.assertAll();
    }

}
