package com.ContactList.demo.Reporitories;

import com.ContactList.demo.DTOs.ContactDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactsRepository extends JpaRepository<ContactDTO, Long> {

}
