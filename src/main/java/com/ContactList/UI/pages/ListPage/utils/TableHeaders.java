package com.ContactList.UI.pages.ListPage.utils;

import lombok.Getter;

@Getter
public enum TableHeaders {

    NAME("Name"),
    BIRTHDATE("Birthdate"),
    EMAIL("Email"),
    PHONE("Phone"),
    ADDRESS("Address"),
    CITY_STATE_POSTAL_CODE("City, State/Province, Postal Code"),
    COUNTRY("Country");

    private final String columnName;

    TableHeaders(String columnName) {
        this.columnName = columnName;
    }
}
