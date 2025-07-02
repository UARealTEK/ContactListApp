package com.ContactList.UI.pages.ListPage.utils;

import lombok.Getter;

@Getter
public enum TableHeaders {

    NAME(1),
    BIRTHDATE(2),
    EMAIL(3),
    PHONE(4),
    ADDRESS(5),
    CITY_STATE_POSTAL_CODE(6),
    COUNTRY(7);

    private final int index;

    TableHeaders(int index) {
        this.index = index;
    }
}
