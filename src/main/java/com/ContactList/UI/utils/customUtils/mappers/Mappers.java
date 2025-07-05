package com.ContactList.UI.utils.customUtils.mappers;

import com.ContactList.API.core.payloads.ContactsPayloads.ContactsBodyPayload;
import com.ContactList.UI.pages.ListPage.utils.TableHeaders;
import lombok.Getter;

import java.util.Map;
import java.util.function.BiConsumer;

public class Mappers {

    @Getter
    private static final Map<TableHeaders, BiConsumer<ContactsBodyPayload, String>> TABLE_FIELD_MAPPINGS = Map.of(
            TableHeaders.BIRTHDATE,ContactsBodyPayload::setBirthdate,
            TableHeaders.EMAIL, ContactsBodyPayload::setEmail,
            TableHeaders.PHONE, ContactsBodyPayload::setPhone,
            TableHeaders.COUNTRY, ContactsBodyPayload::setCountry
    );

    @Getter
    private static final Map<String, BiConsumer<ContactsBodyPayload, String>> FORM_FIELD_MAPPINGS = Map.of(
            "#firstName", ContactsBodyPayload::setFirstName,
            "#lastName", ContactsBodyPayload::setLastName,
            "#birthdate", ContactsBodyPayload::setBirthdate,
            "#email", ContactsBodyPayload::setEmail,
            "#phone", ContactsBodyPayload::setPhone,
            "#city", ContactsBodyPayload::setCity,
            "#stateProvince", ContactsBodyPayload::setStateProvince,
            "#postalCode", ContactsBodyPayload::setPostalCode,
            "#country", ContactsBodyPayload::setCountry
    );
}
