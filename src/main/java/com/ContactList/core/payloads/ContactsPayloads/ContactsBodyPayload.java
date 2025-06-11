package com.ContactList.core.payloads.ContactsPayloads;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

//TODO: work on it!
@Data
public class ContactsBodyPayload {

    @JsonProperty("_id")
    private String id;
    private String firstName;
    private String lastName;
    private String birthdate;
    private String email;
    private String phone;
    private String street;
    private String city;
    private String stateProvince;
    private String postalCode;
    private String country;
    private String owner;
    @JsonProperty("__v")
    private String version;

    Map<String,String> streetFields = new HashMap<>();

    @JsonAnySetter
    public void collectStreetFields(String key, String value) {
        if (key.startsWith("street")) {
            streetFields.put(key,value);
        }
    }
}
