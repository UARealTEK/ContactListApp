package com.ContactList.tests.api;

import com.ContactList.core.payloads.UserPayloads.UserBodyPayload;
import com.ContactList.core.responses.userResponses.UserResponse;
import com.ContactList.core.services.UserService;
import com.ContactList.utils.dataManagement.DataGenerator;
import com.ContactList.utils.helpers.UserApiHelper;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.assertj.core.api.SoftAssertions;

@Tag("api")
@Tag("users")
public class UserTests {

    @Owner("Volodymyr")
    @Feature("UserAPI")
    @DisplayName("Request for adding a User")
    @Story("addUser request")
    @Test
    public void checkAddUser() {
        SoftAssertions soft = new SoftAssertions();
        UserBodyPayload payload = DataGenerator.getRandomUserPayload();
        Response response = new UserService().addUserRequest(payload);

        System.out.println(response.getBody().asPrettyString());

        soft.assertThat(response.getStatusCode()).isEqualTo(201);

        soft.assertAll();
    }

    @Owner("Volodymyr")
    @Feature("UserAPI")
    @DisplayName("Request for getting a User")
    @Story("getUser request")
    @Test
    public void checkGetUser() {
        SoftAssertions soft = new SoftAssertions();
        Response response = new UserService().getUserProfile(UserApiHelper.createRandomUser());

        soft.assertThat(response.getStatusCode()).isEqualTo(200);

        System.out.println(response.getBody().asPrettyString());
        soft.assertAll();
    }

    @Owner("Volodymyr")
    @Feature("UserAPI")
    @DisplayName("Request for updating a User")
    @Story("putUser request")
    @Test
    public void checkUpdateUser() {
        SoftAssertions soft = new SoftAssertions();
        UserResponse user = UserApiHelper.createRandomUser();
        Response patch = new UserService().patchUserRequest(DataGenerator.getRandomUserPayload(), user);

        soft.assertThat(user.getUser().getId()).isEqualTo(patch.as(UserResponse.User.class).getId());
        soft.assertThat(patch.getStatusCode()).isEqualTo(200);

        soft.assertAll();
    }

    @Owner("Volodymyr")
    @Feature("UserAPI")
    @DisplayName("Request for logging out of a system as a User")
    @Story("postUser request to logout endpoint")
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

    @Owner("Volodymyr")
    @Feature("UserAPI")
    @DisplayName("Request for logging in a system as an existing User")
    @Story("postUser request to login endpoint")
    @Test
    public void checkLoginUser() {
        SoftAssertions soft = new SoftAssertions();

        Response response = new UserService().loginUser(DataGenerator.getUserLoginPayload());

        System.out.println(response.getBody().as(UserResponse.class).toString());
        soft.assertThat(response.getStatusCode()).isEqualTo(200);

        soft.assertAll();
    }

    @Owner("Volodymyr")
    @Feature("UserAPI")
    @DisplayName("Request for removing a User")
    @Story("deleteUser request")
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
