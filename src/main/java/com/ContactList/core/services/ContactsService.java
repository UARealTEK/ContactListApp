package com.ContactList.core.services;

import com.ContactList.config.enums.headers.Headers;
import com.ContactList.core.payloads.ContactsPayloads.ContactsBodyPayload;
import com.ContactList.core.responses.contactsResponses.ContactResponse;
import com.ContactList.core.responses.userResponses.UserResponse;
import com.ContactList.utils.dataManagement.DataGenerator;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;

import java.util.List;
import java.util.Map;

/**
 * Parameters -> for current implementation -> only Objects are used as parameters
 * I might overload methods to allow Tokens being passed directly as Strings later
 * Return types -> only RESPONSE for now. To allow assertions for status codes in Tests
 *
 * FYI:
 *    For now -> generating RANDOM user Payload in some methods (e.g {@code addContactRequest()})
 *    Might improve it later so it accepts a preferred Payload as an argument ({@code ContactsBodyPayload})
 */

public class ContactsService extends BaseService {

    private static final String BASE_PATH = "contacts";

    public Response addContactRequest(UserResponse user) {
        ContactsBodyPayload payload = DataGenerator.getRandomContactPayload();
        System.out.printf("user data: %s%n", user.getToken());
        return postRequest(payload, Map.of(Headers.AUTHORIZATION.getHeader(), user.getToken()), BASE_PATH);
    }

    public Response addRichContactRequest(UserResponse user) {
        ContactsBodyPayload payload = DataGenerator.getRandomRichContactPayload();
        return postRequest(payload, Map.of(Headers.AUTHORIZATION.getHeader(), user.getToken()), BASE_PATH);
    }

    public Response getAllContactsRequest(UserResponse user) {
        Map<String,String> headers = Map.of(Headers.AUTHORIZATION.getHeader(), user.getToken());
        return getRequest(BASE_PATH,headers);
    }

    public List<ContactResponse> getAllContacts(UserResponse userResponse) {
        Response response = getAllContactsRequest(userResponse);
        return response.as(new TypeRef<List<ContactResponse>>() {});
    }

    /**
     * @param user -> used for obtaining a Token. Token needs to be shared across all mutual requests
     * @param contact -> used to obtain an ID of already added Contact. For now -> passing a whole Contact
     *                instead of raw Token. (passing Raw token is OK as well, but I prefer passing an object for now)
     * @return -> returns simple response which will contain data for the Contact (which ID was used as an argument)
     */
    public Response getContactRequest(UserResponse user, ContactResponse contact) {
        Map<String,String> headers = Map.of(Headers.AUTHORIZATION.getHeader(), user.getToken());
        String path = BASE_PATH + "/" + contact.getId();
        return getRequest(path,headers);
    }

    public Response putContactRequest(UserResponse user, ContactsBodyPayload payload, ContactResponse contactID) {
        Map<String,String> headers = Map.of(Headers.AUTHORIZATION.getHeader(), user.getToken());
        String path = BASE_PATH + "/" + contactID.getId();
        return putRequest(headers,payload,path);
    }

    public Response patchContactRequest(UserResponse user, ContactsBodyPayload payload, ContactResponse contactID) {
        Map<String,String> headers = Map.of(Headers.AUTHORIZATION.getHeader(), user.getToken());
        String path = BASE_PATH + "/" + contactID.getId();
        return patchRequest(headers,payload,path);
    }

    public Response deleteContactRequest(UserResponse user, ContactResponse contact) {
        Map<String,String> headers = Map.of(Headers.AUTHORIZATION.getHeader(), user.getToken());
        String path = BASE_PATH + "/" + contact.getId();
        return deleteRequest(path,headers);
    }
}
