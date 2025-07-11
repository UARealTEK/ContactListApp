package com.ContactList.UI.utils.endpoints;

import lombok.Getter;

import static com.ContactList.UI.utils.Managers.ConfigurationManager.config;

@Getter
public enum PageEndpoints {

    CONTACT_LIST("contactList"),
    ADD_USER("addUser"),
    LOGIN("login"),
    ADD_CONTACT("addContact");

    private final String endpoint;

    PageEndpoints(String endpoint) {
        this.endpoint = endpoint;
    }

    public static String getFullContactListURL() {
        return config().baseURL() + CONTACT_LIST.getEndpoint();
    }

    public static String getFullSignUpURL() {
        return config().baseURL() + ADD_USER.getEndpoint();
    }

    public static String getFullLoginURL() {
        return config().baseURL() + LOGIN.getEndpoint();
    }

    public static String getFullAddContactURL() {
        return config().baseURL() + ADD_CONTACT.getEndpoint();
    }
}
