package com.ContactList.utils.mappers;

import com.ContactList.API.core.payloads.ContactsPayloads.ContactsBodyPayload;
import com.ContactList.UI.pages.ListPage.utils.TableHeaders;
import com.github.javafaker.Faker;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

import static com.ContactList.API.utils.dataManagement.DataGenerator.getRandomBirthday;

/**
 * <p>Custom utility that is designed to provide Maps that contain field ID's (required for navigation using Locator)<p>
 *     <p>and BiConsumer / Function which are used to trace / fill GENERALLY ContactBodyPayload data<p>
 */
public class Mappers {

    private static final Faker faker = new Faker();

    @Getter
    public static final Map<String, Consumer<ContactsBodyPayload>> contactPayloadFieldSetters = Map.of(
            "firstName", payload -> payload.setFirstName(faker.name().firstName().split(" ")[0]),
            "birthdate", payload -> payload.setBirthdate(getRandomBirthday()),
            "lastName", payload -> payload.setLastName(faker.name().lastName().split(" ")[0]),
            "email", payload -> payload.setEmail(faker.internet().emailAddress()),
            "phone", payload -> payload.setPhone(faker.number().digits(7)),
            "city", payload -> payload.setCity(faker.address().city().split(" ")[0]),
            "stateProvince", payload -> payload.setStateProvince(faker.address().state().split(" ")[0]),
            "postalCode", payload -> payload.setPostalCode(faker.address().zipCode()),
            "country", payload -> payload.setCountry(faker.address().country().split(" ")[0])
    );

    @Getter
    public static final Map<TableHeaders, BiConsumer<ContactsBodyPayload, String>> TABLE_FIELD_MAPPINGS = Map.of(
            TableHeaders.BIRTHDATE,ContactsBodyPayload::setBirthdate,
            TableHeaders.EMAIL, ContactsBodyPayload::setEmail,
            TableHeaders.PHONE, ContactsBodyPayload::setPhone,
            TableHeaders.COUNTRY, ContactsBodyPayload::setCountry
    );

    @Getter
    public static final Map<String, BiConsumer<ContactsBodyPayload, String>> FORM_FIELD_MAPPINGS = Map.of(
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
    public static final Map<String, Function<ContactsBodyPayload, String>> FORM_FIELD_EDITOR_MAPPINGS = Map.of(
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
