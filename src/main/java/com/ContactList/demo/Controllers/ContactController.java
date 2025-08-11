package com.ContactList.demo.Controllers;

import com.ContactList.demo.DTOs.ContactDTO;
import com.ContactList.demo.services.ContactService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/contacts")
    public List<ContactDTO> getContacts(@RequestHeader("Authorization") String token) {
        return contactService.getAllContacts();
    }
}
