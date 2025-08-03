package com.ContactList.demo.DTOs;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class UserDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "external_id")
    private String externalID;
    private String firstName;
    private String lastName;
    private String email;
    private String version;

    private String token;
}
