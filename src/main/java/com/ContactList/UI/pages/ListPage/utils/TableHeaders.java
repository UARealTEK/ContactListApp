package com.ContactList.UI.pages.ListPage.utils;

import lombok.Getter;

@Getter
public enum TableHeaders {

    NAME(0),
    BIRTHDATE(1),
    EMAIL(2),
    PHONE(3),
    ADDRESS(4),
    CITY_STATE_POSTAL_CODE(5),
    COUNTRY(6);

    private final int index;

    TableHeaders(int index) {
        this.index = index;
    }
}
