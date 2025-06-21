package com.ContactList.API.core.payloads.UserPayloads;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserLoginPayload {

    private String email;
    private String password;
}
