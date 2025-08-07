package com.ContactList.demo.Reporitories;

import com.ContactList.demo.DTOs.ContactDTO;
import com.ContactList.demo.DTOs.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContactsRepository extends JpaRepository<ContactDTO, Long> {


    ContactDTO findTopByOrderByIdDesc();

    @Query("SELECT c.id FROM ContactDTO c")
    List<Long> findAllContactIds();

    @NonNull
    Optional<ContactDTO> findByExternalID(@NonNull String id);

    Optional<ContactDTO> findByEmail(@NonNull String email);

}
