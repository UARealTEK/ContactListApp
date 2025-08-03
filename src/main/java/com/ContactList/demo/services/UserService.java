package com.ContactList.demo.services;

import com.ContactList.demo.DTOs.UserDTO;
import com.ContactList.demo.Reporitories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
