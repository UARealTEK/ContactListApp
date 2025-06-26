package com.ContactList.config.enums.endpoints;

public enum ContactEndpoints {

    ME("me"),
    LOGIN("login"),
    LOGOUT("logout");

    private final String endpoint;

    ContactEndpoints(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getEndpoint() {
        return endpoint;
    }
}
