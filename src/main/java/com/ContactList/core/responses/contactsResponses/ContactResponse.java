package com.ContactList.core.responses.contactsResponses;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactResponse {

    @JsonProperty("_id")
    private String id;
    private String firstName;
    private String lastName;
    private String birthdate;
    private String email;
    private String phone;
    private String city;
    private String stateProvince;
    private String postalCode;
    private String country;
    private String owner;
    @JsonProperty("__v")
    private String version;

    private final Map<String,String> streetFields = new HashMap<>();

    @JsonAnySetter
    public void collectStreetFields(String key, String value) {
        if (key.startsWith("street")) {
            streetFields.put(key,value);
        }
    }

    @JsonAnyGetter
    public Map<String,String> getStreetFields() {
        return streetFields;
    }
}
