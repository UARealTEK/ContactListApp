package com.ContactList.demo.services;

import com.ContactList.demo.DTOs.UserDTO;
import com.ContactList.demo.Reporitories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> getUsers() {
        return userRepository.findAll();
    }
}
