package com.ContactList.demo.utils;

import com.ContactList.API.core.responses.userResponses.UserResponse;
import com.ContactList.demo.DTOs.UserDTO;
import com.ContactList.demo.Reporitories.UserRepository;
import io.restassured.response.Response;

public class UserDbUtils {

    public static void saveUserToDB(Response response, UserRepository repository) {
        UserResponse userResponse = response.as(UserResponse.class);
        UserResponse.User user = userResponse.getUser();

        UserDTO dto = new UserDTO();
        dto.setExternalID(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setVersion(user.getVersion());
        dto.setToken(userResponse.getToken());

        repository.save(dto);
    }
}
