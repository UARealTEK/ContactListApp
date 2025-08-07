package com.ContactList.demo.services;

import com.ContactList.API.core.responses.contactsResponses.ContactResponse;
import com.ContactList.UI.utils.customUtils.serializers.JsonUtils;
import com.ContactList.demo.DTOs.ContactDTO;
import com.ContactList.demo.Reporitories.ContactsRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.microsoft.playwright.Response;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ContactService {

    private final ContactsRepository contactsRepository;

    public ContactService(ContactsRepository contactsRepository) {
        this.contactsRepository = contactsRepository;
    }

    public List<ContactDTO> getAllContacts() {
        return contactsRepository.findAll();
    }

    public Long getContactCount() {
        return contactsRepository.count();
    }

    public ContactDTO getLatestContact() {
        return contactsRepository.findTopByOrderByIdDesc();
    }

    public ContactDTO getContactById(long id) {
        return contactsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("contact was not found with id " + id));
    }

    public ContactDTO getContactByEmail(String email) {
        return contactsRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("contact was not found with email " + email));
    }

    public ContactDTO getContactByExternalId(String id) {
        return contactsRepository.findByExternalID(id)
                .orElseThrow(() -> new RuntimeException("contact was not found with external id " + id));
    }

    /**
     *
     * @param response -> will extract the Response from the internal API call to /contacts
     */
    public void saveContactToDB(Response response) {
        List<ContactResponse> contactResponse = JsonUtils.parseList(response.text(), new TypeReference<>() {});
        ContactResponse contact = contactResponse.getFirst();

        ContactDTO dto = new ContactDTO();
        dto.setExternalID(contact.getId());
        dto.setFirstName(contact.getFirstName());
        dto.setLastName(contact.getLastName());
        dto.setBirthdate(contact.getBirthdate());
        dto.setEmail(contact.getEmail());
        dto.setPhone(contact.getPhone());
        dto.setCity(contact.getCity());
        dto.setStateProvince(contact.getStateProvince());
        dto.setPostalCode(contact.getPostalCode());
        dto.setCountry(contact.getCountry());
        dto.setOwner(contact.getOwner());
        dto.setVersion(contact.getVersion());

        Map<String,String> streetFields = contact.getStreetFields();

        streetFields.forEach((key, value) -> {
            if (key.equalsIgnoreCase("street1") && !value.isBlank()) {
                dto.setStreet1(value);
            } else if (key.equalsIgnoreCase("street2") && !value.isBlank()) {
                dto.setStreet2(value);
            }
        });

        contactsRepository.save(dto);
    }
}
