package com.ContactList.demo.services;

import com.ContactList.API.core.responses.userResponses.UserResponse;
import com.ContactList.demo.DTOs.UserDTO;
import com.ContactList.demo.Reporitories.UserRepository;
import io.restassured.response.Response;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public long getUserCount() {
        return userRepository.count();
    }

    public UserDTO getLatestUser() {
        return userRepository.findTopByOrderByIdDesc();
    }

    public UserDTO getUserByID(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("the user was not found with ID " + id));
    }

    public void saveUserToDB(Response response) {
        UserResponse userResponse = response.as(UserResponse.class);
        UserResponse.User user = userResponse.getUser();

        UserDTO dto = new UserDTO();
        dto.setExternalID(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setVersion(user.getVersion());
        dto.setToken(userResponse.getToken());

        userRepository.save(dto);
    }

    @Transactional
    public void updateUserInDB(Response response) {
        UserResponse.User user = response.as(UserResponse.User.class);

        UserDTO dto = userRepository.findByExternalID(user.getId())
                .orElseThrow(() -> new RuntimeException("The user was not found with ID -> " + Long.valueOf(user.getId())));

        if (!user.getFirstName().equals(dto.getFirstName())) {
            dto.setFirstName(user.getFirstName());
        }

        if (!user.getLastName().equals(dto.getLastName())) {
            dto.setLastName(user.getLastName());
        }

        if (!user.getEmail().equals(dto.getEmail())) {
            dto.setEmail(user.getEmail());
        }

        if (!user.getVersion().equals(dto.getVersion())) {
            dto.setVersion(user.getVersion());
        }
    }
}
