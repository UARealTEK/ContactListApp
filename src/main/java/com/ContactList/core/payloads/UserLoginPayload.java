package com.ContactList.core.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserLoginPayload {

    private String email;
    private String password;
}
