package com.ContactList.UI.utils.endpoints;

import lombok.Getter;

import static com.ContactList.UI.config.ConfigurationManager.config;

@Getter
public enum ListPageEndpoints {

    CONTACT_LIST("contactList");

    private String endpoint;

    private ListPageEndpoints(String endpoint) {
        this.endpoint = endpoint;
    }

    public static String getFullContactListURL() {
        return config().baseURL() + CONTACT_LIST.getEndpoint();
    }
}
