package com.ContactList.API.core.payloads.ContactsPayloads;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

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
}
