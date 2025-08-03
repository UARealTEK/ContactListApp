package com.ContactList.tests.db;

import com.ContactList.API.core.payloads.UserPayloads.UserBodyPayload;
import com.ContactList.API.core.responses.userResponses.UserResponse;
import com.ContactList.API.core.services.UserService;
import com.ContactList.API.utils.dataManagement.DataGenerator;
import com.ContactList.API.utils.helpers.UserApiHelper;
import com.ContactList.demo.DTOs.UserDTO;
import com.ContactList.demo.Reporitories.UserRepository;
import com.ContactList.demo.utils.CustomDBAssertions;
import com.ContactList.demo.utils.UserDbUtils;
import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ThreadLocalRandom;

@SpringBootTest(classes = com.ContactList.demo.Application.class)
@Tag("db")
public class DBUserTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void checkAddUser() {
        SoftAssertions soft = new SoftAssertions();
        UserBodyPayload payload = DataGenerator.getRandomSafeUserPayload();

        long dbRowCountBefore = userRepository.count();

        Response response = new UserService().addUserRequest(payload);
        UserDbUtils.saveUserToDB(response,userRepository);

        Long dbRowCountAfter = userRepository.count();

        soft.assertThat(response.getStatusCode()).isEqualTo(201);
        soft.assertThat(dbRowCountAfter).isEqualTo(dbRowCountBefore + 1);
        soft.assertThat(CustomDBAssertions.isUserDTOEqualToResponseBody(userRepository, response.as(UserResponse.class))).isTrue();
        soft.assertThat(response.as(UserResponse.class).getToken()).isEqualTo(userRepository.findTopByOrderByIdDesc().getToken());

        soft.assertAll();
    }

    @Test
    public void checkGetUser() {
        SoftAssertions soft = new SoftAssertions();
        long userID = ThreadLocalRandom.current().nextLong(1, userRepository.count());
        UserDTO dto = userRepository
                .findById(userID).
                orElseThrow(() -> new RuntimeException("The user was not found with ID -> " + userID));

        Response response = new UserService().getUserProfile(dto);

        System.out.println(response.getBody().asPrettyString());
        System.out.println(dto);

        soft.assertThat(response.getStatusCode()).isEqualTo(200);
        soft.assertThat(CustomDBAssertions.isSpecificUserDTOEqualToResponseBody(userRepository, response.as(UserResponse.User.class), userID)).isTrue();

        soft.assertAll();
    }

}
