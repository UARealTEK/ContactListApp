package com.ContactList.demo.Controllers;

import com.ContactList.API.core.responses.userResponses.UserResponse;
import com.ContactList.demo.DTOs.UserDTO;
import com.ContactList.demo.services.UserService;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private UserService userService;

    public UserController (UserService userService) {
        this.userService = userService;
    }

    public List<UserDTO> getAllUsers() {
        return userService.getUsers();
    }
}
