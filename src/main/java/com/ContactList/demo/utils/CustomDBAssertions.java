package com.ContactList.demo.utils;

import com.ContactList.API.core.responses.userResponses.UserResponse;
import com.ContactList.demo.DTOs.UserDTO;
import com.ContactList.demo.Reporitories.UserRepository;

import java.util.Map;

public class CustomDBAssertions {

    /**
     * Not expressive but it gets the job done
     * might add debugging later
     */
    public static boolean isUserDTOEqualToResponseBody(UserRepository repository, UserResponse response) {
        UserDTO dto = repository.findTopByOrderByIdDesc();
        Map<String,String> map = Map.of(
                dto.getFirstName(), response.getUser().getFirstName(),
                dto.getLastName(), response.getUser().getLastName(),
                dto.getExternalID(), response.getUser().getId(),
                dto.getEmail(), response.getUser().getEmail(),
                dto.getVersion(), response.getUser().getVersion());

        return map.entrySet().stream().allMatch(entry -> entry.getKey().equals(entry.getValue()));
    }

    public static boolean isSpecificUserDTOEqualToResponseBody(UserRepository repository, UserResponse.User response, long userDTO_ID) {
        UserDTO dto = repository
                .findById(userDTO_ID).
                orElseThrow(() -> new RuntimeException("The user was not found with ID -> " + userDTO_ID));
        Map<String,String> map = Map.of(
                dto.getFirstName(), response.getFirstName(),
                dto.getLastName(), response.getLastName(),
                dto.getExternalID(), response.getId(),
                dto.getEmail(), response.getEmail(),
                dto.getVersion(), response.getVersion());

        return map.entrySet().stream().allMatch(entry -> entry.getKey().equals(entry.getValue()));
    }
}
