package com.ContactList.API.enums.endpoints;

public enum UserEndpoints {

    ME("me"),
    LOGIN("login"),
    LOGOUT("logout");

    private final String endpoint;

    UserEndpoints(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getEndpoint() {
        return endpoint;
    }
}
