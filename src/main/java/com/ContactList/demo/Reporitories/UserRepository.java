package com.ContactList.demo.Reporitories;

import com.ContactList.demo.DTOs.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//TODO: think about the exact type of values that we should work with
@Repository
public interface UserRepository extends JpaRepository<UserDTO, Long> {
    Optional<UserDTO> findUserByEmail(String name);
}
