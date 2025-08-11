package com.ContactList.UI.pages.ListPage.utils;

import lombok.Getter;

@Getter
public enum ListPageEndpoints {

    CONTACTS("contacts");

    private final String endpoint;

    ListPageEndpoints(String endpoint) {
        this.endpoint = endpoint;
    }
}
