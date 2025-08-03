package com.ContactList.demo.services;

import com.ContactList.demo.DTOs.ContactDTO;
import com.ContactList.demo.Reporitories.ContactsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    private final ContactsRepository contactsRepository;

    public ContactService(ContactsRepository contactsRepository) {
        this.contactsRepository = contactsRepository;
    }

    public List<ContactDTO> getAllContacts() {
        return contactsRepository.findAll();
    }
}
