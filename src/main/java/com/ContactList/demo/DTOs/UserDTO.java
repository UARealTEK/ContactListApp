package com.ContactList.demo.DTOs;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class UserDTO {

    @Id
    private String id;  // if your DB uses String IDs; else use Long/UUID
    private String firstName;
    private String lastName;
    private String email;
    private String version;
}
