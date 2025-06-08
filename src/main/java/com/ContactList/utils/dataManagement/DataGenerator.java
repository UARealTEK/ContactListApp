package com.ContactList.utils.dataManagement;

import com.ContactList.core.payloads.UserPayload;
import com.github.javafaker.Faker;

public class DataGenerator {

    private static final Faker faker = new Faker();

    public static UserPayload getRandomUserPayload() {
        String firstName = faker.name().name();
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress();
        String password = faker.internet().password();
        return new UserPayload(firstName,lastName,email,password);
    }
}
