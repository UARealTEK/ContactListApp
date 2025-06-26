package com.ContactList.core.payloads.UserPayloads;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserBodyPayload {

    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public UserBodyPayload(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserPayload{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
