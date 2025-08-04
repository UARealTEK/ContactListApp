package com.ContactList.tests.db;

import com.ContactList.API.core.payloads.UserPayloads.UserBodyPayload;
import com.ContactList.API.core.responses.userResponses.UserResponse;
import com.ContactList.API.core.services.UserService;
import com.ContactList.API.utils.dataManagement.DataGenerator;
import com.ContactList.demo.DTOs.UserDTO;
import com.ContactList.demo.utils.CustomDBAssertions;
import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootTest(classes = com.ContactList.demo.Application.class)
@Tag("db")
public class DBUserTests {

    @Autowired
    private com.ContactList.demo.services.UserService userService;

    @Test
    public void checkAddUser() {
        SoftAssertions soft = new SoftAssertions();
        UserBodyPayload payload = DataGenerator.getRandomSafeUserPayload();

        long dbRowCountBefore = userService.getUserCount();

        Response response = new UserService().addUserRequest(payload);
        userService.saveUserToDB(response);

        Long dbRowCountAfter = userService.getUserCount();

        soft.assertThat(response.getStatusCode()).isEqualTo(201);
        soft.assertThat(dbRowCountAfter).isEqualTo(dbRowCountBefore + 1);
        soft.assertThat(CustomDBAssertions.isUserDTOEqualToResponseBody(userService, response.as(UserResponse.class))).isTrue();
        soft.assertThat(response.as(UserResponse.class).getToken()).isEqualTo(userService.getLatestUser().getToken());

        soft.assertAll();
    }

    @Test
    public void checkGetUser() {
        SoftAssertions soft = new SoftAssertions();
        List<Long> ids = userService.getAllUserIDs();

        if (ids.isEmpty()) {
            throw new RuntimeException("No users found in the DB");
        }

        long userID = ids.get(ThreadLocalRandom.current().nextInt(ids.size()));
        UserDTO dto = userService.getUserByID(userID);

        Response response = new UserService().getUserProfile(dto);

        soft.assertThat(response.getStatusCode()).isEqualTo(200);
        soft.assertThat(CustomDBAssertions.isSpecificUserDTOEqualToResponseBody(userService, response.as(UserResponse.User.class), userID))
                .isTrue();

        soft.assertAll();
    }

    @Test
    public void checkUpdateUser() {
        SoftAssertions soft = new SoftAssertions();
        long dbRowCountBefore = userService.getUserCount();
        List<Long> ids = userService.getAllUserIDs();

        if (ids.isEmpty()) {
            throw new RuntimeException("No users found in the DB");
        }

        long userID = ids.get(ThreadLocalRandom.current().nextInt(ids.size()));
        UserBodyPayload user = DataGenerator.getRandomUserPayload();
        UserDTO dto = userService.getUserByID(userID);

        Response response = new UserService().patchUserRequest(user, dto);
        System.out.println(response.getBody().asPrettyString());
        userService.updateUserInDB(response);

        long dbRowCountAfter = userService.getUserCount();

        soft.assertThat(response.getStatusCode()).isEqualTo(200);
        soft.assertThat(CustomDBAssertions.isSpecificUserDTOEqualToResponseBody(userService, response.as(UserResponse.User.class), userID))
                .isTrue();
        soft.assertThat(dbRowCountBefore == dbRowCountAfter).isTrue();


        soft.assertAll();
    }

    @Test
    public void checkDeleteUser() {
        SoftAssertions soft = new SoftAssertions();
        long dbRowCountBefore = userService.getUserCount();
        List<Long> ids = userService.getAllUserIDs();

        if (ids.isEmpty()) {
            throw new RuntimeException("No users found in the DB");
        }

        long userID = ids.get(ThreadLocalRandom.current().nextInt(ids.size()));
        UserDTO dto = userService.getUserByID(userID);

        Response response = new UserService().deleteUser(dto);
        userService.deleteUserFromDB(dto);

        long dbRowCountAfter = userService.getUserCount();

        soft.assertThat(response.getStatusCode()).isEqualTo(200);
        soft.assertThat(dbRowCountAfter).isEqualTo(dbRowCountBefore -1);
        soft.assertThatThrownBy(() -> userService.getUserByEmail(dto.getEmail()))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("the user was not found with email ");

        soft.assertAll();
    }

}
