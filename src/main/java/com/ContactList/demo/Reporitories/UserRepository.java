package com.ContactList.demo.Reporitories;

import com.ContactList.demo.DTOs.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<UserDTO, Long> {

    UserDTO findTopByOrderByIdDesc();

    @NonNull
    Optional<UserDTO> findById(@NonNull Long id);

    @NonNull
    Optional<UserDTO> findByExternalID(@NonNull String id);
}
