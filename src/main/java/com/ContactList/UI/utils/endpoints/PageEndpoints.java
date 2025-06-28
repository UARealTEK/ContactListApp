package com.ContactList.UI.utils.endpoints;

import lombok.Getter;

import static com.ContactList.UI.config.ConfigurationManager.config;

@Getter
public enum PageEndpoints {

    CONTACT_LIST("contactList"),
    ADD_USER("addUser");

    private String endpoint;

    private PageEndpoints(String endpoint) {
        this.endpoint = endpoint;
    }

    public static String getFullContactListURL() {
        return config().baseURL() + CONTACT_LIST.getEndpoint();
    }

    public static String getFullSignUpURL() {
        return config().baseURL() + ADD_USER.getEndpoint();
    }
}
