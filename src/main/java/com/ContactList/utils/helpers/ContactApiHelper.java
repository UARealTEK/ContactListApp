package com.ContactList.utils.helpers;

import com.ContactList.core.responses.contactsResponses.ContactResponse;
import com.ContactList.core.responses.userResponses.UserResponse;
import com.ContactList.core.services.ContactsService;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


public class ContactApiHelper {

    /**
     * FYI:
     * this method will return something if the passed in UserResponse already contains at least 1 user in its Contacts list
     *
     * @param user -> requires a {@code UserResponse} object to retrieve a Token in order to perform a chain of requests
     * @return -> randomly selected {@code ContactResponse} from the list of ALREADY added Contacts to the passed in {@code UserResponse}
     */
    public static ContactResponse getAnyContact(UserResponse user) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        List<ContactResponse> contacts = new ContactsService().getAllContacts(user);

        if (!contacts.isEmpty()) {
            return contacts.get(random.nextInt(contacts.size()));
        } else throw new IllegalArgumentException("No users were found in the contact list for the passed In User");
    }
}
