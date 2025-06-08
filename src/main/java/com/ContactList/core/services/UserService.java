package com.ContactList.core.services;

import com.ContactList.config.enums.endpoints.UserEndpoints;
import com.ContactList.config.enums.headers.Headers;
import com.ContactList.core.payloads.UserPayload;
import com.ContactList.utils.dataManagement.DataSerializer;
import io.restassured.http.Header;
import io.restassured.response.Response;

import java.util.Map;

public class UserService extends BaseService {
    private static final String BASE_PATH = "users";

    public Response addUserRequest(UserPayload payload) {
        return postRequest(DataSerializer.serializeUserPayload(payload), BASE_PATH);
    }

    public Response getUserProfile(String token) {
        String path = BASE_PATH + "/" + UserEndpoints.ME.getEndpoint();
        Map<String,String> headers = Map.of(Headers.AUTHORIZATION.getHeader(), token);
        return getRequest(path, headers);
    }

    public Response patchUserRequest(UserPayload payload, String token) {
        String path = BASE_PATH + "/" + UserEndpoints.ME.getEndpoint();
        return patchRequest(token, DataSerializer.serializeUserPayload(payload), path);
    }

}
