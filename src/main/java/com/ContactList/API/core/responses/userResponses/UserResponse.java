package com.ContactList.API.core.responses.userResponses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class UserResponse {
    private User user;
    private String token;

    @Data
    public static class User {
        @JsonProperty("_id")
        private String id;
        private String firstName;
        private String lastName;
        private String email;
        @JsonProperty("__v")
        private String version;
    }


}
