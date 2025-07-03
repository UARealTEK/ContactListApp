package com.ContactList.API.core.payloads.ContactsPayloads;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ContactsBodyPayload {

    private final Map<String,String> streetFields = new HashMap<>();

    private String firstName;
    private String lastName;
    private String birthdate;
    private String email;
    private String phone;
    private String city;
    private String stateProvince;
    private String postalCode;
    private String country;

    @JsonAnySetter
    public void collectStreetFields(String key, String value) {
        if (key.startsWith("street")) {
            streetFields.put(key,value);
        }
    }

    @JsonAnyGetter
    public Map<String, String> getDynamicFields() {
        return streetFields;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ContactsBodyPayload that)) return false;

        return Objects.equals(firstName, that.firstName)
                && Objects.equals(lastName, that.lastName)
                && Objects.equals(birthdate, that.birthdate)
                && Objects.equals(email, that.email)
                && Objects.equals(phone, that.phone)
                && Objects.equals(city, that.city)
                && Objects.equals(stateProvince, that.stateProvince)
                && Objects.equals(postalCode, that.postalCode)
                && Objects.equals(country, that.country)
                && Objects.equals(streetFields, that.streetFields);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                firstName, lastName, birthdate, email, phone,
                city, stateProvince, postalCode, country, streetFields
        );
    }
}
