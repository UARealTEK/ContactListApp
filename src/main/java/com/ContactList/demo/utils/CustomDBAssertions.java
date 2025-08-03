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
    public static boolean isUserDTOEqualToResponse(UserRepository repository, UserResponse response) {
        UserDTO dto = repository.findTopByOrderByIdDesc();
        Map<String,String> map = Map.of(
                dto.getFirstName(), response.getUser().getFirstName(),
                dto.getLastName(), response.getUser().getLastName(),
                dto.getExternalID(), response.getUser().getId(),
                dto.getEmail(), response.getUser().getEmail(),
                dto.getVersion(), response.getUser().getVersion(),
                dto.getToken(), response.getToken());

        return map.entrySet().stream().allMatch(entry -> entry.getKey().equals(entry.getValue()));
    }
}
