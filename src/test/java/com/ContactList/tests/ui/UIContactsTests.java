package com.ContactList.tests.ui;

import com.ContactList.API.core.payloads.ContactsPayloads.ContactsBodyPayload;
import com.ContactList.API.core.payloads.UserPayloads.UserBodyPayload;
import com.ContactList.API.utils.dataManagement.DataGenerator;
import com.ContactList.UI.pages.AddContactPage.AddContactPage;
import com.ContactList.UI.BaseClasses.BaseTest;
import com.ContactList.UI.pages.ContactDetailsPage.ContactDetailsPage;
import com.ContactList.UI.pages.EditContactPage.EditContactPage;
import com.ContactList.UI.pages.ListPage.ListPage;
import com.ContactList.UI.pages.LoginPage.LoginPage;
import com.ContactList.UI.utils.endpoints.PageEndpoints;
import io.qameta.allure.*;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("ui")
public class UIContactsTests extends BaseTest {

    @Link(name = "Just for a test", url = "https://www.youtube.com/watch?v=DUUgn0fMMnw")
    @Owner("Volodymyr")
    @Feature("Contacts UI")
    @DisplayName("logging out A user after logging in")
    @Description("logging out after signing up")
    @Story("ContactsUI")
    @Test
    public void checkLogoutProcessAfterSignUp() {
        SoftAssertions soft = new SoftAssertions();
        UserBodyPayload user = DataGenerator.getRandomSafeUserPayload();

        LoginPage startPage = loginPage
                .openSignUpPage()
                .signUpUser(user)
                .logout();

        soft.assertThat(startPage.getCurrentURL()).isEqualTo(PageEndpoints.getFullDefaultURL());
        soft.assertAll();
    }

    @Link(name = "Just for a test", url = "https://www.youtube.com/watch?v=DUUgn0fMMnw")
    @Owner("Volodymyr")
    @Feature("Contacts UI")
    @DisplayName("opening add contacts page")
    @Description("opening add contacts page to create a potential user")
    @Story("ContactsUI")
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

    @Link(name = "Just for a test", url = "https://www.youtube.com/watch?v=DUUgn0fMMnw")
    @Owner("Volodymyr")
    @Feature("Contacts UI")
    @DisplayName("Adding a contact using predefined data")
    @Description("Adding a contact")
    @Story("ContactsUI")
    @Test
    public void checkSimpleContactAddingFlow() {
        SoftAssertions soft = new SoftAssertions();
        UserBodyPayload user = DataGenerator.getRandomSafeUserPayload();
        ContactsBodyPayload payload = DataGenerator.getRandomContactPayload();

        ListPage listPage = loginPage
                .openSignUpPage()
                .signUpUser(user)
                .openAddContactPage().addContact(payload);

        soft.assertThat(listPage.getCurrentURL()).isEqualTo(PageEndpoints.getFullContactListURL());
        soft.assertAll();
    }

    @Link(name = "Just for a test", url = "https://www.youtube.com/watch?v=DUUgn0fMMnw")
    @Owner("Volodymyr")
    @Feature("Contacts UI")
    @DisplayName("Adding a contact using predefined data along with Dynamic Fields")
    @Description("Adding a contact including dynamic fields")
    @Story("ContactsUI")
    @Test
    public void checkRichContactAddFlow() {
        SoftAssertions soft = new SoftAssertions();
        UserBodyPayload user = DataGenerator.getRandomSafeUserPayload();
        ContactsBodyPayload richPayload = DataGenerator.getRandomRichContactPayload();

        ListPage listPage = loginPage
                .openSignUpPage()
                .signUpUser(user)
                .openAddContactPage()
                .addContact(richPayload);

        soft.assertThat(listPage.getCurrentURL()).isEqualTo(PageEndpoints.getFullContactListURL());

        soft.assertAll();
    }

    @Link(name = "Just for a test", url = "https://www.youtube.com/watch?v=DUUgn0fMMnw")
    @Owner("Volodymyr")
    @Feature("Contacts UI")
    @DisplayName("Canceling out of opened Contact Add Page")
    @Description("Clicking on 'Cancel' button on the Contact Add Page")
    @Story("ContactsUI")
    @Test
    public void checkCancelContactAddFlow() {
        SoftAssertions soft = new SoftAssertions();
        UserBodyPayload user = DataGenerator.getRandomSafeUserPayload();

        ListPage page = loginPage
                .openSignUpPage()
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

    @Link(name = "Just for a test", url = "https://www.youtube.com/watch?v=DUUgn0fMMnw")
    @Owner("Volodymyr")
    @Feature("Contacts UI")
    @DisplayName("Viewing a contacts details page which contains predefined added data")
    @Description("Opening contact Details page")
    @Story("ContactsUI")
    @RepeatedTest(3)
    public void checkViewContactDetailsPage() {
        SoftAssertions soft = new SoftAssertions();
        UserBodyPayload user = DataGenerator.getRandomSafeUserPayload();
        ContactsBodyPayload richPayload = DataGenerator.getRandomRichContactPayload();

        ContactDetailsPage detailsPage = loginPage
                .openSignUpPage()
                .signUpUser(user)
                .openAddContactPage()
                .addContact(richPayload)
                .openContactDetailsPage();

        soft.assertThat(richPayload).isEqualTo(detailsPage.getForm().getContactPayload());
        soft.assertThat(detailsPage.getCurrentURL()).isEqualTo(PageEndpoints.getFullContactDetailsURL());

        ListPage listPage = detailsPage.openContactListPage();

        soft.assertThat(richPayload).isEqualTo(listPage.getTable().getContactData(1));

        soft.assertAll();
    }

    @Link(name = "Just for a test", url = "https://www.youtube.com/watch?v=DUUgn0fMMnw")
    @Owner("Volodymyr")
    @Feature("Contacts UI")
    @DisplayName("Opening Edit Contact page for the already added contact")
    @Description("Opening Edit Contact page")
    @Story("ContactsUI")
    @Test
    public void checkOpeningEditFormForAddedContact() {
        SoftAssertions soft = new SoftAssertions();
        UserBodyPayload user = DataGenerator.getRandomSafeUserPayload();
        ContactsBodyPayload richPayload = DataGenerator.getRandomRichContactPayload();


        EditContactPage contactPage = loginPage
                .openSignUpPage()
                .signUpUser(user)
                .openAddContactPage()
                .addContact(richPayload)
                .openContactDetailsPage()
                .openEditContact();

        soft.assertThat(contactPage.getCurrentURL()).isEqualTo(PageEndpoints.getFullEditContactURL());
        soft.assertAll();
    }

    @Link(name = "Just for a test", url = "https://www.youtube.com/watch?v=DUUgn0fMMnw")
    @Owner("Volodymyr")
    @Feature("Contacts UI")
    @DisplayName("Editing already added user with predefined different set of data")
    @Description("Editing already added user")
    @Story("ContactsUI")
    @Test
    public void checkContactEditing() {
        SoftAssertions soft = new SoftAssertions();
        UserBodyPayload user = DataGenerator.getRandomSafeUserPayload();
        ContactsBodyPayload payload = DataGenerator.getRandomRichContactPayload();
        ContactsBodyPayload replacementPayload = DataGenerator.getRandomRichContactPayload();

        ContactDetailsPage contactPage = loginPage
                .openSignUpPage()
                .signUpUser(user)
                .openAddContactPage()
                .addContact(payload)
                .openContactDetailsPage()
                .openEditContact()
                .editContact(replacementPayload);

        soft.assertThat(contactPage.getCurrentURL()).isEqualTo(PageEndpoints.getFullContactDetailsURL());
        soft.assertThat(contactPage.getForm().getContactPayload()).isEqualTo(replacementPayload);

        soft.assertAll();
    }

    @Link(name = "Just for a test", url = "https://www.youtube.com/watch?v=DUUgn0fMMnw")
    @Owner("Volodymyr")
    @Feature("Contacts UI")
    @DisplayName("Partially editing a contact(only one specific field) using predefined data")
    @Description("Partially editing a contact")
    @Story("ContactsUI")
    @Test
    public void checkPartialContactEditing() {
        SoftAssertions soft = new SoftAssertions();
        UserBodyPayload user = DataGenerator.getRandomSafeUserPayload();
        ContactsBodyPayload payload = DataGenerator.getRandomRichContactPayload();
        ContactsBodyPayload replacementPayload = DataGenerator.getRandomContactPayloadEntry();

        ContactDetailsPage contactPage = loginPage
                .openSignUpPage()
                .signUpUser(user)
                .openAddContactPage()
                .addContact(payload)
                .openContactDetailsPage()
                .openEditContact()
                .partialContactEdit(replacementPayload);

        payload.mergeFrom(replacementPayload);

        soft.assertThat(contactPage.getCurrentURL()).isEqualTo(PageEndpoints.getFullContactDetailsURL());
        soft.assertThat(contactPage.getForm().getContactPayload()).isEqualTo(payload);

        soft.assertAll();
    }

    @Link(name = "Just for a test", url = "https://www.youtube.com/watch?v=DUUgn0fMMnw")
    @Owner("Volodymyr")
    @Feature("Contacts UI")
    @DisplayName("Deleting existing contact from the contacts list")
    @Description("Deleting existing contact")
    @Story("ContactsUI")
    @Test
    public void checkDeletingExistingContact() {
        SoftAssertions soft = new SoftAssertions();
        UserBodyPayload user = DataGenerator.getRandomSafeUserPayload();
        ContactsBodyPayload richPayload = DataGenerator.getRandomRichContactPayload();

        ListPage listPage = loginPage
                .openSignUpPage()
                .signUpUser(user)
                .openAddContactPage()
                .addContact(richPayload)
                .openContactDetailsPage()
                .deleteContact();

        soft.assertThat(listPage.getTable().getAmountOfRows()).isEqualTo(0);

        soft.assertAll();
    }

    @Link(name = "Just for a test", url = "https://www.youtube.com/watch?v=DUUgn0fMMnw")
    @Owner("Volodymyr")
    @Feature("Contacts UI")
    @DisplayName("Canceling out of the Edit Contact Page")
    @Description("Canceling out of the Edit")
    @Story("ContactsUI")
    @Test
    public void checkCancelingOutOfEditContactForm() {
        SoftAssertions soft = new SoftAssertions();
        UserBodyPayload user = DataGenerator.getRandomSafeUserPayload();
        ContactsBodyPayload payload = DataGenerator.getRandomRichContactPayload();

        ContactDetailsPage detailsPage = loginPage
                .openSignUpPage()
                .signUpUser(user)
                .openAddContactPage()
                .addContact(payload)
                .openContactDetailsPage()
                .openEditContact()
                .cancelContactEdit();

        soft.assertThat(detailsPage.getCurrentURL()).isEqualTo(PageEndpoints.getFullContactDetailsURL());

        soft.assertAll();
    }

    @Link(name = "Just for a test", url = "https://www.youtube.com/watch?v=DUUgn0fMMnw")
    @Owner("Volodymyr")
    @Feature("Contacts UI")
    @DisplayName("Logging out from the opened Contact Details page")
    @Description("Logging out from the Contact Details page")
    @Story("ContactsUI")
    @Test
    public void checkLogoutFromContactDetails() {
        SoftAssertions soft = new SoftAssertions();
        UserBodyPayload user = DataGenerator.getRandomSafeUserPayload();
        ContactsBodyPayload richPayload = DataGenerator.getRandomRichContactPayload();

        LoginPage contactDetailsPage = loginPage
                .openSignUpPage()
                .signUpUser(user)
                .openAddContactPage()
                .addContact(richPayload)
                .openContactDetailsPage()
                .logout();

        soft.assertThat(contactDetailsPage.getCurrentURL()).isEqualTo(PageEndpoints.getFullDefaultURL());

        soft.assertAll();
    }

}
