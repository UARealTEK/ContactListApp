package com.ContactList.demo.utils;

import com.ContactList.API.core.payloads.ContactsPayloads.ContactsBodyPayload;
import com.ContactList.API.core.responses.userResponses.UserResponse;
import com.ContactList.demo.DTOs.ContactDTO;
import com.ContactList.demo.DTOs.UserDTO;
import com.ContactList.demo.services.ContactService;
import com.ContactList.demo.services.UserService;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class CustomDBAssertions {

    /**
     * Not expressive but it gets the job done
     * might add debugging later
     */
    public static boolean isUserDTOEqualToResponseBody(UserService service, UserResponse response) {
        UserDTO dto = service.getLatestUser();
        UserResponse.User user = response.getUser();

        return Objects.equals(dto.getFirstName(), user.getFirstName()) &&
                Objects.equals(dto.getLastName(), user.getLastName()) &&
                Objects.equals(dto.getExternalID(), user.getId()) &&
                Objects.equals(dto.getEmail(), user.getEmail()) &&
                Objects.equals(dto.getVersion(), user.getVersion());
    }


    public static boolean isSpecificUserDTOEqualToResponseBody(UserService service, UserResponse.User response, long userDTO_ID) {
        UserDTO dto = service.getUserByID(userDTO_ID);

        return Objects.equals(dto.getFirstName(), response.getFirstName()) &&
                Objects.equals(dto.getLastName(), response.getLastName()) &&
                Objects.equals(dto.getExternalID(), response.getId()) &&
                Objects.equals(dto.getEmail(), response.getEmail()) &&
                Objects.equals(dto.getVersion(), response.getVersion());
    }

    public static boolean isLatestContactDTOEqualToUI(ContactService service, ContactsBodyPayload payload) {
        ContactDTO dto = service.getLatestContact();
       boolean nonDynamicFieldsEqual =
               Objects.equals(dto.getFirstName(), payload.getFirstName()) &&
                       Objects.equals(dto.getLastName(), payload.getLastName()) &&
                       Objects.equals(dto.getBirthdate(), payload.getBirthdate()) &&
                       Objects.equals(dto.getEmail(), payload.getEmail()) &&
                       Objects.equals(dto.getPhone(), payload.getPhone()) &&
                       Objects.equals(dto.getStateProvince(), payload.getStateProvince()) &&
                       Objects.equals(dto.getPostalCode(), payload.getPostalCode()) &&
                       Objects.equals(dto.getCountry(), payload.getCountry()) &&
                       Objects.equals(dto.getCity(), payload.getCity());

        Map<String, String> dynamicFields = payload.getDynamicFields();
        Map<String, String> dtoDynamicFields = Map.of(
                "street1", Optional.ofNullable(dto.getStreet1()).orElse(""),
                "street2", Optional.ofNullable(dto.getStreet2()).orElse("")
        );

        boolean isDynamicFieldsEqual = Objects.equals(dtoDynamicFields,dynamicFields);

        return nonDynamicFieldsEqual && isDynamicFieldsEqual;
    }

    public static boolean isSpecificContactDTOEqualToUI(ContactService service, ContactsBodyPayload payload, long contactDTO_ID) {
        ContactDTO dto = service.getContactById(contactDTO_ID);
        boolean nonDynamicFieldsEqual =
                Objects.equals(dto.getFirstName(), payload.getFirstName()) &&
                        Objects.equals(dto.getLastName(), payload.getLastName()) &&
                        Objects.equals(dto.getBirthdate(), payload.getBirthdate()) &&
                        Objects.equals(dto.getEmail(), payload.getEmail()) &&
                        Objects.equals(dto.getPhone(), payload.getPhone()) &&
                        Objects.equals(dto.getStateProvince(), payload.getStateProvince()) &&
                        Objects.equals(dto.getPostalCode(), payload.getPostalCode()) &&
                        Objects.equals(dto.getCountry(), payload.getCountry()) &&
                        Objects.equals(dto.getCity(), payload.getCity());

        Map<String, String> dynamicFields = payload.getDynamicFields();
        Map<String,String> dtoDynamicFields = Map.of(
                "street1", dto.getStreet1(),
                "street2", dto.getStreet2()
        );

        boolean isDynamicFieldsEqual = Objects.equals(dtoDynamicFields,dynamicFields);

        return nonDynamicFieldsEqual && isDynamicFieldsEqual;
    }


}
