package com.ContactList.UI.utils.customUtils.mappers;

import com.ContactList.API.core.payloads.ContactsPayloads.ContactsBodyPayload;
import com.ContactList.UI.pages.ListPage.utils.TableHeaders;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

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

    @Getter
    private static final Map<String, Function<ContactsBodyPayload, String>> FORM_FIELD_EDITOR_MAPPINGS = Map.of(
            "#firstName", ContactsBodyPayload::getFirstName,
            "#lastName", ContactsBodyPayload::getLastName,
            "#birthdate", ContactsBodyPayload::getBirthdate,
            "#email", ContactsBodyPayload::getEmail,
            "#phone", ContactsBodyPayload::getPhone,
            "#city", ContactsBodyPayload::getCity,
            "#stateProvince",ContactsBodyPayload::getStateProvince,
            "#postalCode", ContactsBodyPayload::getPostalCode,
            "#country", ContactsBodyPayload::getCountry
    );

    public static Map<String,String> CONTACT_PAYLOAD_ENTRY_MAPPING(ContactsBodyPayload payload) {
        Map<String,String> resultPayload = new HashMap<>();

        getFORM_FIELD_EDITOR_MAPPINGS().forEach((key,getter) -> {
            String value = getter.apply(payload);
            if (value != null && !value.isBlank()) {
                resultPayload.put(key,value);
            }
        });

        return resultPayload;
    }
}
