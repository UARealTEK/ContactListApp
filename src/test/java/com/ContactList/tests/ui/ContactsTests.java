package com.ContactList.tests.ui;

import com.ContactList.API.core.payloads.ContactsPayloads.ContactsBodyPayload;
import com.ContactList.API.core.payloads.UserPayloads.UserBodyPayload;
import com.ContactList.API.utils.dataManagement.DataGenerator;
import com.ContactList.UI.pages.AddContactPage.AddContactPage;
import com.ContactList.UI.BaseClasses.BaseTest;
import com.ContactList.UI.pages.ContactDetailsPage.ContactDetailsPage;
import com.ContactList.UI.pages.ListPage.ListPage;
import com.ContactList.UI.utils.endpoints.PageEndpoints;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

//TODO:
// - think about merging all necessary chain method calls into one (for proper SRP principle to work)
// - Question for Stanislav -> better to leave as it is? Or to indeed merge those chain calls into one method? how does it work?
public class ContactsTests extends BaseTest {

    @Test
    public void checkOpeningAddContactPage() {
        SoftAssertions soft = new SoftAssertions();
        UserBodyPayload payload = DataGenerator.getRandomSafeUserPayload();

        AddContactPage page = loginPage
                .openSignUpPage()
                .signUpUser(payload)
                .openAddContactPage();

        soft.assertThat(page.getCurrentURL()).isEqualTo(PageEndpoints.getFullAddContactURL());
        soft.assertAll();
    }

    @Test
    public void checkSimpleContactAddingFlow() {
        SoftAssertions soft = new SoftAssertions();
        UserBodyPayload user = DataGenerator.getRandomSafeUserPayload();
        ContactsBodyPayload payload = DataGenerator.getRandomContactPayload();

        ListPage listPage = loginPage.openSignUpPage()
                .signUpUser(user)
                .openAddContactPage().addContact(payload);

        soft.assertThat(listPage.getCurrentURL()).isEqualTo(PageEndpoints.getFullContactListURL());
        soft.assertAll();
    }

    @Test
    public void checkRichContactAddFlow() {
        SoftAssertions soft = new SoftAssertions();
        UserBodyPayload user = DataGenerator.getRandomSafeUserPayload();
        ContactsBodyPayload richPayload = DataGenerator.getRandomRichContactPayload();

        ListPage listPage = loginPage.openSignUpPage()
                .signUpUser(user)
                .openAddContactPage()
                .addContact(richPayload);

        soft.assertThat(listPage.getCurrentURL()).isEqualTo(PageEndpoints.getFullContactListURL());

        soft.assertAll();
    }

    @Test
    public void checkCancelContactAddFlow() {
        SoftAssertions soft = new SoftAssertions();
        UserBodyPayload user = DataGenerator.getRandomSafeUserPayload();

        ListPage page = loginPage.openSignUpPage().signUpUser(user).openAddContactPage().cancelAddingContact();

        soft.assertThat(page.getCurrentURL()).isEqualTo(PageEndpoints.getFullContactListURL());

        soft.assertAll();
    }

    //TODO: verify the data in the test
    @Test
    public void checkViewContactDetailsPage() {
        SoftAssertions soft = new SoftAssertions();
        UserBodyPayload user = DataGenerator.getRandomSafeUserPayload();
        ContactsBodyPayload richPayload = DataGenerator.getRandomRichContactPayload();

        ContactDetailsPage detailsPage = loginPage.openSignUpPage()
                .signUpUser(user)
                .openAddContactPage()
                .addContact(richPayload)
                .openContactDetailsPage();
        soft.assertThat(richPayload.equals(detailsPage.getForm().getContactPayload())).isTrue();
        soft.assertThat(detailsPage.getCurrentURL()).isEqualTo(PageEndpoints.getFullContactDetailsURL());

        soft.assertAll();
    }


}
