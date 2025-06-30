package com.ContactList.API.core.services;

import com.ContactList.API.enums.endpoints.UserEndpoints;
import com.ContactList.API.enums.headers.Headers;
import com.ContactList.API.core.payloads.UserPayloads.UserBodyPayload;
import com.ContactList.API.core.payloads.UserPayloads.UserLoginPayload;
import com.ContactList.API.core.responses.userResponses.UserResponse;
import io.restassured.response.Response;

import java.util.Map;

public class UserService extends BaseService {
    private static final String BASE_PATH = "users";

    public Response addUserRequest(UserBodyPayload payload) {
        return postRequest(payload, BASE_PATH);
    }

    public Response getUserProfile(UserResponse userToken) {
        String path = BASE_PATH + "/" + UserEndpoints.ME.getEndpoint();
        Map<String,String> headers = Map.of(Headers.AUTHORIZATION.getHeader(), userToken.getToken());
        return getRequest(path, headers);
    }

    /**
     * @param payload -> payload which will be used as a data replacement
     * FYI:
     * PATCH request method is only allowed for changing the user data. PUT is not permitted
     */
    public Response patchUserRequest(UserBodyPayload payload, UserResponse userToken) {
        String path = BASE_PATH + "/" + UserEndpoints.ME.getEndpoint();
        Map<String,String> headers = Map.of(Headers.AUTHORIZATION.getHeader(), userToken.getToken());
        return patchRequest(headers, payload, path);
    }

    public Response logoutUser(UserResponse userToken) {
        String path = BASE_PATH + "/" + UserEndpoints.LOGOUT.getEndpoint();
        Map<String,String> headers = Map.of(Headers.AUTHORIZATION.getHeader(), userToken.getToken());
        return postRequest(headers, path);
    }

    public Response loginUser(UserLoginPayload payload) {
        String path = BASE_PATH + "/" + UserEndpoints.LOGIN.getEndpoint();
        return postRequest(payload, path);
    }

    public Response deleteUser(UserResponse userToken) {
        String path = BASE_PATH + "/" + UserEndpoints.ME.getEndpoint();
        Map<String,String> header = Map.of(Headers.AUTHORIZATION.getHeader(), userToken.getToken());
        return deleteRequest(path,header);
    }

}
