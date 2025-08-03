package com.ContactList.demo.Reporitories;

import com.ContactList.demo.DTOs.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserDTO, Long> {

    UserDTO findTopByOrderByIdDesc();
    Optional<UserDTO> findById(Long id);
}
