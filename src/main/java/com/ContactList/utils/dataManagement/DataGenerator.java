package com.ContactList.utils.dataManagement;

import com.ContactList.core.payloads.UserBodyPayload;
import com.github.javafaker.Faker;

public class DataGenerator {

    private static final Faker faker = new Faker();

    public static UserBodyPayload getRandomUserPayload() {
        String firstName = faker.name().name();
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress();
        String password = faker.internet().password();
        return new UserBodyPayload(firstName,lastName,email,password);
    }
}
