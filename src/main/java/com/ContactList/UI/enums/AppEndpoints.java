package com.ContactList.UI.enums;

import lombok.Getter;

@Getter
public enum AppEndpoints {

    BASE_PATH("https://thinking-tester-contact-list.herokuapp.com/"),
    USER_PAGE("contactList");

    private String endpoint;

    private AppEndpoints(String endpoint) {
        this.endpoint = endpoint;
    }

}
