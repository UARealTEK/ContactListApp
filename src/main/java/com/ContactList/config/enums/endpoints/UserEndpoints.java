package com.ContactList.config.enums.endpoints;

public enum UserEndpoints {

    ME("me");

    private final String endpoint;

    UserEndpoints(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getEndpoint() {
        return endpoint;
    }
}
