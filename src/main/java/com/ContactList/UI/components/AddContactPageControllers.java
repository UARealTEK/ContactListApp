package com.ContactList.UI.components;

import com.ContactList.API.core.payloads.ContactsPayloads.ContactsBodyPayload;
import com.microsoft.playwright.Page;

//TODO:
// - complete full logic
// - think how to implement flow using RichContactPayload
public class AddContactPageControllers extends BaseComponent {

    public AddContactPageControllers(Page page) {
        super(page);
    }

    public void fillFirstName(ContactsBodyPayload payload) {
        page.fill("id=firstName", payload.getFirstName());
    }


    public void fillLastName(ContactsBodyPayload payload) {
        page.fill("id=lastName", payload.getLastName());
    }

    public void fillBirthdate(ContactsBodyPayload payload) {
        page.fill("id=birthdate", payload.getBirthdate());
    }

    public void fillEmail(ContactsBodyPayload payload) {
        page.fill("id=email", payload.getEmail());
    }

    public void fillPhone(ContactsBodyPayload payload) {
        page.fill("id=phone", payload.getPhone());
    }

    public void fillCity(ContactsBodyPayload payload) {
        page.fill("id=city", payload.getCity());
    }

    public void fillStateProvince(ContactsBodyPayload payload) {
        page.fill("id=stateProvince", payload.getStateProvince());
    }

    public void fillPostalCode(ContactsBodyPayload payload) {
        page.fill("id=postalCode", payload.getPostalCode());
    }

    public void fillCountry(ContactsBodyPayload payload) {
        page.fill("id=stateProvince", payload.getCountry());
    }

    public void clickSubmit() {
        page.click("id=submit");
    }

    public void clickCancel() {
        page.click("id=cancel");
    }

}
