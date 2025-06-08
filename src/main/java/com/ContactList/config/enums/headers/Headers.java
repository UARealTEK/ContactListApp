package com.ContactList.config.enums.headers;

import lombok.Getter;

@Getter
public enum Headers {

    AUTHORIZATION("Authorization");

    private final String header;

    Headers(String header) {
        this.header = header;
    }

}
