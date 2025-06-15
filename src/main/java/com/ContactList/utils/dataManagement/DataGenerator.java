package com.ContactList.utils.dataManagement;

import com.ContactList.core.payloads.ContactsPayloads.ContactsBodyPayload;
import com.ContactList.core.payloads.UserPayloads.UserBodyPayload;
import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DataGenerator {

    private static final Faker faker = new Faker();

    public static UserBodyPayload getRandomUserPayload() {
        String firstName = faker.name().name();
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress();
        String password = faker.internet().password();
        return new UserBodyPayload(firstName,lastName,email,password);
    }

    public static ContactsBodyPayload getRandomContactPayload() {
        Date birthday = faker.date().birthday(10,40);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyy-MM-dd");
        LocalDate date = birthday.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        String formattedBirthday = date.format(formatter);

        String firstName = faker.name().name().substring(0,10);
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress();
        String phone = faker.number().digits(10);
        String city = faker.address().city();
        String stateProvince = faker.address().state();
        String postalCode = faker.address().zipCode();
        String country = faker.address().country();
        return new ContactsBodyPayload(firstName, lastName, formattedBirthday, email, phone, city, stateProvince, postalCode, country);
    }

    public static ContactsBodyPayload getRandomRichContactPayload() {
        ContactsBodyPayload body = getRandomContactPayload();
        body.collectStreetFields("street1", faker.address().streetAddress());
        body.collectStreetFields("street2", faker.address().secondaryAddress());
        return body;
    }
}
