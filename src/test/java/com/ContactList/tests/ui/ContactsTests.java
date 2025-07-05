package com.ContactList.tests.ui;

import com.ContactList.API.core.payloads.ContactsPayloads.ContactsBodyPayload;
import com.ContactList.API.core.payloads.UserPayloads.UserBodyPayload;
import com.ContactList.API.utils.dataManagement.DataGenerator;
import com.ContactList.UI.pages.AddContactPage.AddContactPage;
import com.ContactList.UI.BaseClasses.BaseTest;
import com.ContactList.UI.pages.ContactDetailsPage.ContactDetailsPage;
import com.ContactList.UI.pages.EditContactPage.EditContactPage;
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

        ListPage page = loginPage.openSignUpPage()
                .signUpUser(user)
                .openAddContactPage()
                .cancelAddingContact();

        soft.assertThat(page.getCurrentURL()).isEqualTo(PageEndpoints.getFullContactListURL());

        soft.assertAll();
    }

    /**
     * The {@code checkViewContactDetailsPage()} Test contains
     * Additional check for verification of the Data in the Contacts Table
     */

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

        soft.assertThat(richPayload).isEqualTo(detailsPage.getForm().getContactPayload());
        soft.assertThat(detailsPage.getCurrentURL()).isEqualTo(PageEndpoints.getFullContactDetailsURL());

        ListPage listPage = detailsPage.openContactListPage();

        soft.assertThat(richPayload).isEqualTo(listPage.getTable().getContactData(1));

        System.out.println(richPayload);
        System.out.println(listPage.getTable().getContactData(1));

        soft.assertAll();
    }

    @Test
    public void checkOpeningEditFormForAddedContact() {
        SoftAssertions soft = new SoftAssertions();
        UserBodyPayload user = DataGenerator.getRandomSafeUserPayload();
        ContactsBodyPayload richPayload = DataGenerator.getRandomRichContactPayload();


        EditContactPage contactPage = loginPage.openSignUpPage()
                .signUpUser(user)
                .openAddContactPage()
                .addContact(richPayload)
                .openContactDetailsPage()
                .openEditContact();

        soft.assertThat(contactPage.getCurrentURL()).isEqualTo(PageEndpoints.getFullEditContactURL());
        soft.assertAll();
    }

    @Test
    public void checkDeletingExistingContact() {
        SoftAssertions soft = new SoftAssertions();
        UserBodyPayload user = DataGenerator.getRandomSafeUserPayload();
        ContactsBodyPayload richPayload = DataGenerator.getRandomRichContactPayload();

        ListPage listPage = loginPage.openSignUpPage()
                .signUpUser(user)
                .openAddContactPage()
                .addContact(richPayload)
                .openContactDetailsPage()
                .deleteContact();

        soft.assertThat(listPage.getTable().getAmountOfRows()).isEqualTo(0);

        soft.assertAll();
    }

}
