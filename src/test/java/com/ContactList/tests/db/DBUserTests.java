package com.ContactList.tests.db;

import com.ContactList.API.core.payloads.UserPayloads.UserBodyPayload;
import com.ContactList.API.core.responses.userResponses.UserResponse;
import com.ContactList.API.core.services.UserService;
import com.ContactList.API.utils.dataManagement.DataGenerator;
import com.ContactList.demo.Reporitories.UserRepository;
import com.ContactList.demo.utils.CustomDBAssertions;
import com.ContactList.demo.utils.UserDbUtils;
import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
        soft.assertThat(CustomDBAssertions.isUserDTOEqualToResponse(userRepository, response.as(UserResponse.class))).isTrue();

        System.out.printf("The user data is: %s%n", response.as(UserResponse.class).getUser().toString());
        System.out.printf("Token is -> %s", response.as(UserResponse.class).getToken());
        System.out.printf("Last injected user is -> %s", userRepository.findTopByOrderByIdDesc().toString());

        soft.assertAll();
    }

}
