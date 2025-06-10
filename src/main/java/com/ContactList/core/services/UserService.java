package com.ContactList.core.services;

import com.ContactList.config.enums.endpoints.UserEndpoints;
import com.ContactList.config.enums.headers.Headers;
import com.ContactList.core.payloads.UserBodyPayload;
import com.ContactList.core.payloads.UserLoginPayload;
import com.ContactList.core.payloads.UserResponse;
import com.ContactList.utils.dataManagement.DataSerializer;
import io.restassured.response.Response;

import java.util.Map;
//TODO: "Review the logic of using serialization (try working with serialization in RestAssured using .as(UserResponse.class)).
//TODO: not pass raw Strings! Use data from objects that are passed as arguments."

public class UserService extends BaseService {
    private static final String BASE_PATH = "users";

    public Response addUserRequest(UserBodyPayload payload) {
        return postRequest(payload, BASE_PATH);
    }

    public Response getUserProfile(String token) {
        String path = BASE_PATH + "/" + UserEndpoints.ME.getEndpoint();
        Map<String,String> headers = Map.of(Headers.AUTHORIZATION.getHeader(), token);
        return getRequest(path, headers);
    }

    public Response patchUserRequest(UserBodyPayload payload, String token) {
        String path = BASE_PATH + "/" + UserEndpoints.ME.getEndpoint();
        Map<String,String> headers = Map.of(Headers.AUTHORIZATION.getHeader(), token);
        return patchRequest(headers, DataSerializer.serializeUserPayload(payload), path);
    }

    public Response logoutUser(UserResponse userToken) {
        String path = BASE_PATH + "/" + UserEndpoints.LOGOUT.getEndpoint();
        return postRequest(userToken.getToken(), path);
    }

    public Response loginUser(UserLoginPayload payload) {
        String path = BASE_PATH + "/" + UserEndpoints.LOGIN.getEndpoint();
        return postRequest(payload, path);
    }

    public Response deleteUser(String token) {
        String path = BASE_PATH + "/" + UserEndpoints.ME.getEndpoint();
        Map<String,String> header = Map.of(Headers.AUTHORIZATION.getHeader(), token);
        return deleteRequest(path,header);
    }

}
