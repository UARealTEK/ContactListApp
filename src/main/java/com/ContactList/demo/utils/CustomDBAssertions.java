package com.ContactList.demo.utils;

import com.ContactList.API.core.responses.userResponses.UserResponse;
import com.ContactList.demo.DTOs.UserDTO;
import com.ContactList.demo.services.UserService;

import java.util.Map;

public class CustomDBAssertions {

    /**
     * Not expressive but it gets the job done
     * might add debugging later
     */
    public static boolean isUserDTOEqualToResponseBody(UserService service, UserResponse response) {
        UserDTO dto = service.getLatestUser();
        Map<String,String> map = Map.of(
                dto.getFirstName(), response.getUser().getFirstName(),
                dto.getLastName(), response.getUser().getLastName(),
                dto.getExternalID(), response.getUser().getId(),
                dto.getEmail(), response.getUser().getEmail(),
                dto.getVersion(), response.getUser().getVersion());

        return map.entrySet().stream().allMatch(entry -> entry.getKey().equals(entry.getValue()));
    }

    public static boolean isSpecificUserDTOEqualToResponseBody(UserService service, UserResponse.User response, long userDTO_ID) {
        UserDTO dto = service.getUserByID(userDTO_ID);
        Map<String,String> map = Map.of(
                dto.getFirstName(), response.getFirstName(),
                dto.getLastName(), response.getLastName(),
                dto.getExternalID(), response.getId(),
                dto.getEmail(), response.getEmail(),
                dto.getVersion(), response.getVersion());

        return map.entrySet().stream().allMatch(entry -> entry.getKey().equals(entry.getValue()));
    }
}
