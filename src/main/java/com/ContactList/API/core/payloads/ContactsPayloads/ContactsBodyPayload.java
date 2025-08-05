package com.ContactList.API.core.payloads.ContactsPayloads;

import com.ContactList.utils.mappers.Mappers;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.*;
import java.util.stream.Collectors;

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

    /**
     * custom utility method that helps to copy data from the payload
     * which was passed in as an argument into original payload
     * on which the method was called
     * @param that -> supplement payload which serves as data source for the copy operation
     *            into original payload
     */
    public void mergeFrom(ContactsBodyPayload that) {
        Mappers.getFORM_FIELD_MAPPINGS().forEach((key,setter) -> {
            String value = Mappers.getFORM_FIELD_EDITOR_MAPPINGS().get(key).apply(that);
            if (value != null && !value.isBlank()) {
                setter.accept(this,value);
            }
        });

        this.getDynamicFields()
                .putAll(that.getDynamicFields()
                        .entrySet()
                        .stream()
                        .filter(e -> e.getValue() != null && !e.getValue().isBlank())
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
    }

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

        return Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(birthdate, that.birthdate) &&
                Objects.equals(email, that.email) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(city, that.city) &&
                Objects.equals(stateProvince, that.stateProvince) &&
                Objects.equals(postalCode, that.postalCode) &&
                Objects.equals(country, that.country) &&
                streetFieldsEqual(this.streetFields, that.streetFields);
    }

    private boolean streetFieldsEqual(Map<String, String> a, Map<String, String> b) {
        if (a == b) return true;
        if (a == null || b == null) return false;
        if (a.size() != b.size()) return false;

        for (Map.Entry<String, String> entry : a.entrySet()) {
            if (!Objects.equals(entry.getValue(), b.get(entry.getKey()))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(
                firstName,
                lastName,
                birthdate,
                email,
                phone,
                city,
                stateProvince,
                postalCode,
                country
        );

        for (Map.Entry<String, String> entry : streetFields.entrySet()) {
            result = 31 * result + Objects.hash(entry.getKey(), entry.getValue());
        }

        return result;
    }

}
