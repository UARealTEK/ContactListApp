package com.ContactList.UI.utils.customUtils.listeners;

import com.ContactList.UI.pages.ListPage.utils.ListPageEndpoints;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Response;
import lombok.Getter;

/**
 * Custom listener to catch the API calls to some internal endpoints
 * <p>
 * Useful because it allows to track precise response
 */
public class ResponseListeners {

    @Getter
    private static Response capturedContactsResponse;

    public static void attachContactsListener(Page page) {
        page.onResponse(response ->  {
            if(response.url().contains(ListPageEndpoints.CONTACTS.getEndpoint())) {
                capturedContactsResponse = response;
            }
        });
    }

    public static void clear() {
        capturedContactsResponse = null;
    }
}
