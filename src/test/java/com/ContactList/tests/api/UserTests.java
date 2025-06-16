package com.ContactList.tests.api;

import com.ContactList.core.payloads.UserPayloads.UserBodyPayload;
import com.ContactList.core.responses.userResponses.UserResponse;
import com.ContactList.core.services.UserService;
import com.ContactList.utils.dataManagement.DataGenerator;
import com.ContactList.utils.helpers.UserApiHelper;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.assertj.core.api.SoftAssertions;

public class UserTests {

    @Test
    public void checkAddUser() {
        SoftAssertions soft = new SoftAssertions();
        UserBodyPayload payload = DataGenerator.getRandomUserPayload();
        Response response = new UserService().addUserRequest(payload);

        System.out.println(response.getBody().asPrettyString());

        soft.assertThat(response.getStatusCode()).isEqualTo(201);

        soft.assertAll();
    }

    @Test
    public void checkGetUser() {
        SoftAssertions soft = new SoftAssertions();
        Response response = new UserService().getUserProfile(UserApiHelper.createRandomUser());

        soft.assertThat(response.getStatusCode()).isEqualTo(200);

        System.out.println(response.getBody().asPrettyString());
        soft.assertAll();
    }

    @Test
    public void checkUpdateUser() {
        SoftAssertions soft = new SoftAssertions();
        UserResponse user = UserApiHelper.createRandomUser();
        Response patch = new UserService().patchUserRequest(DataGenerator.getRandomUserPayload(), user);

        soft.assertThat(user.getUser().getId()).isEqualTo(patch.as(UserResponse.User.class).getId());
        soft.assertThat(patch.getStatusCode()).isEqualTo(200);

        soft.assertAll();
    }

    @Test
    public void checkLogoutUser() {
        SoftAssertions soft = new SoftAssertions();

        // addUserRequest() -> it CREATES a user. But, by the looks of it, it also LOGS IN the user
        // This thought comes from the fact that executing logout request returns 200 using the provided Token from UserApiHelper.createRandomUser();
        Response response = new UserService().logoutUser(UserApiHelper.createRandomUser());

        soft.assertThat(response.getStatusCode()).isEqualTo(200);

        soft.assertAll();
    }

    /**
     * {@code UserApiHelper.getUserLoginPayload()} provides credentials for a logged-in user.
     * <p>
     * The returned object contains the email and password,
     * which were originally obtained from {@link UserBodyPayload} after executing {@code addUserRequest()}.
     * <p>
     * This is confirmed by the fact that executing a login request with these credentials returns a 200 OK response.
     */

    @Test
    public void checkLoginUser() {
        SoftAssertions soft = new SoftAssertions();

        Response response = new UserService().loginUser(DataGenerator.getUserLoginPayload());

        System.out.println(response.getBody().as(UserResponse.class).toString());
        soft.assertThat(response.getStatusCode()).isEqualTo(200);

        soft.assertAll();
    }

    @Test
    public void checkDeleteUser() {
        SoftAssertions soft = new SoftAssertions();
        UserResponse user = UserApiHelper.createRandomUser();
        Response response = new UserService().deleteUser(user);

        soft.assertThat(response.getStatusCode()).isEqualTo(200);
        soft.assertThat(response.getBody().asString()).isEmpty();

        soft.assertAll();
    }

}
