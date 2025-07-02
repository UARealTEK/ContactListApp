package com.ContactList.UI.utils.endpoints;

import lombok.Getter;

import static com.ContactList.UI.utils.Managers.ConfigurationManager.config;

@Getter
public enum PageEndpoints {

    CONTACT_LIST("contactList"),
    SIGN_UP("addUser"),
    LOGIN("login"),
    ADD_CONTACT("addContact"),
    CONTACT_DETAILS("contactDetails");

    private final String endpoint;

    PageEndpoints(String endpoint) {
        this.endpoint = endpoint;
    }

    public static String getFullContactListURL() {
        return config().baseURL() + CONTACT_LIST.getEndpoint();
    }

    public static String getFullSignUpURL() {
        return config().baseURL() + SIGN_UP.getEndpoint();
    }

    public static String getFullLoginURL() {
        return config().baseURL() + LOGIN.getEndpoint();
    }

    public static String getFullAddContactURL() {
        return config().baseURL() + ADD_CONTACT.getEndpoint();
    }

    public static String getFullContactDetailsURL() {
        return config().baseURL() + CONTACT_DETAILS.getEndpoint();
    }
}
