package com.ContactList.API.utils.dataManagement;

import com.ContactList.API.core.payloads.ContactsPayloads.ContactsBodyPayload;
import com.ContactList.API.core.payloads.UserPayloads.UserBodyPayload;
import com.ContactList.API.core.payloads.UserPayloads.UserLoginPayload;
import com.ContactList.API.core.responses.userResponses.UserResponse;
import com.ContactList.API.core.services.UserService;
import com.ContactList.API.utils.helpers.UserApiHelper;
import com.github.javafaker.Faker;
import io.restassured.response.Response;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;

public class DataGenerator {

    private static final Faker faker = new Faker();

    /**
     * A map of field names to setter actions for {@link ContactsBodyPayload}.
     * <p>
     * Used to populate individual fields with random values for partial updates,
     * preferably in PATCH requests.
     * <p>
     * Note: This approach isn't very practical for frequent changes.
     * If new fields are added to {@code ContactsBodyPayload}, this map
     * must be updated accordingly.
     */

    private static final Map<String, Consumer<ContactsBodyPayload>> contactPayloadFieldSetters = Map.of(
            "firstName", payload -> payload.setFirstName(faker.name().name().substring(0,10)),
            "birthdate", payload -> payload.setBirthdate(getRandomBirthday()),
            "lastName", payload -> payload.setLastName(faker.name().lastName()),
            "email", payload -> payload.setEmail(faker.internet().emailAddress()),
            "phone", payload -> payload.setPhone(faker.number().digits(10)),
            "city", payload -> payload.setCity(faker.address().city()),
            "stateProvince", payload -> payload.setStateProvince(faker.address().state()),
            "postalCode", payload -> payload.setPostalCode(faker.address().zipCode()),
            "country", payload -> payload.setCountry(faker.address().country())
    );

    private static String getRandomBirthday() {
        Date birthday = faker.date().birthday(10,40);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyy-MM-dd");
        LocalDate date = birthday.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return date.format(formatter).trim();
    }

    public static UserBodyPayload getRandomUserPayload() {
        String firstName = faker.name().name().substring(0,3);
        String lastName = faker.name().lastName().substring(0,3);
        String email = faker.internet().emailAddress();
        String password = faker.internet().password();
        return new UserBodyPayload(firstName,lastName,email,password);
    }

    /**
     * <p>Additional method that will generate a unique email address</p>
     * <p>This approach is aimed at creating the user, storing its email and then deleting it completely to ensure email address is preserved</p>
     */
    public static UserBodyPayload getRandomSafeUserPayload() {
        UserBodyPayload payload = DataGenerator.getRandomUserPayload();
        payload.setEmail(DataGenerator.getValidUserEmail());
        return payload;
    }

    public static UserLoginPayload getUserLoginPayload() {
        UserBodyPayload payload = DataGenerator.getRandomSafeUserPayload();
        Response response = new UserService().addUserRequest(payload);

        if (response.getStatusCode() != 201) {
            System.out.println(response.getBody().asPrettyString());
            throw new RuntimeException(String.format("failed to create a user. Status code is -> %s", response.getStatusCode()));
        }

        return new UserLoginPayload(payload.getEmail(),payload.getPassword());
    }

    public static ContactsBodyPayload getRandomContactPayload() {
        String firstName = faker.name().name().substring(0,3).split(" ")[0].trim();
        String lastName = faker.name().lastName().split(" ")[0].trim();
        String email = getValidUserEmail().split(" ")[0].trim();
        String phone = faker.number().digits(10).split(" ")[0].trim();
        String city = faker.address().city().split(" ")[0].trim();
        String stateProvince = faker.address().state().split(" ")[0].trim();
        String postalCode = faker.address().zipCode().split(" ")[0].trim();
        String country = faker.address().country().substring(0,3).split(" ")[0].trim();
        return new ContactsBodyPayload(firstName, lastName, getRandomBirthday(), email, phone, city, stateProvince, postalCode, country);
    }

    public static ContactsBodyPayload getRandomRichContactPayload() {
        ContactsBodyPayload body = getRandomContactPayload();
        body.collectStreetFields("street1", faker.address().streetAddress().trim());
        body.collectStreetFields("street2", faker.address().secondaryAddress().trim());
        return body;
    }

    public static String getValidUserEmail() {
        UserResponse user = UserApiHelper.createRandomUser();
        String validEmail = user.getUser().getEmail();

        new UserService().deleteUser(user);
        return validEmail;
    }

    /**
     * Method which uses the {@code contactPayloadFieldSetters} field to execute (using {@link Consumer}) the selected field entry
     * @return -> {@code ContactBodyPayload} which contains body with only ONE field entry
     * <p>
     * e.g:
     * {
     *     "firstName": "Volodymyr";
     * }
     * <p>
     * Since this method will be preferably used in PATCH requests -> {@code ContactBodyPayload} with single entry is permitted
     */

    public static ContactsBodyPayload getRandomContactPayloadEntry() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        ContactsBodyPayload payload = new ContactsBodyPayload();
        Field[] fields = ContactsBodyPayload.class.getDeclaredFields();

        List<String> fieldNames = Arrays.stream(fields)
                .filter(field -> !Modifier.isFinal(field.getModifiers()))
                .map(Field::getName)
                .toList();

        String selectedEntry = fieldNames.get(random.nextInt(fieldNames.size()));
        contactPayloadFieldSetters
                .getOrDefault(selectedEntry, payload1 -> {throw new RuntimeException("invalid field name value");})
                .accept(payload);

        return payload;
    }
}
