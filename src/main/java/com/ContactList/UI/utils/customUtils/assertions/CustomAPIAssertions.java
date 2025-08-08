package com.ContactList.UI.utils.customUtils.assertions;

import com.ContactList.API.core.payloads.ContactsPayloads.ContactsBodyPayload;
import com.ContactList.UI.utils.customUtils.listeners.ResponseListeners;
import com.ContactList.UI.utils.customUtils.serializers.JsonUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import org.assertj.core.api.SoftAssertions;

import java.util.List;

public class CustomAPIAssertions {

    /**
     *
     * @param soft -> Needed to pick up SoftAssertions and use it outside the testing scope
     * @param payload -> {@code ContactsBodyPayload} object that will be compared to the FIRST (latest) added contact
     *                which is obtained using {@code Response.of()} method
     */

    //TODO: modify the method so it works with exact specified contact (instead of picking up the FIRST from the list)
    public static void assertAddedContact(SoftAssertions soft,ContactsBodyPayload payload) {
        try {
            List<ContactsBodyPayload> payloads = JsonUtils
                    .parseList(ResponseListeners.getCapturedContactsResponse().text(), new TypeReference<>(){});
            System.out.println(payloads.getFirst());
            System.out.println(payload);
            soft.assertThat(payloads.getFirst().equals(payload)).isTrue();
        } catch (RuntimeException e) {
            throw new AssertionError("The payload was empty. Contacts APi call was probably not caught");
        }
    }
}
