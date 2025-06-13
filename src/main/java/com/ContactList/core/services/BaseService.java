package com.ContactList.core.services;

import com.ContactList.config.enums.headers.Headers;
import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class BaseService {

    private static final String BASE_URL = "https://thinking-tester-contact-list.herokuapp.com/";
    private final RequestSpecification requestSpecification;

    public BaseService() {
        RestAssured.config = RestAssuredConfig.config()
                .httpClient(HttpClientConfig.httpClientConfig()
                        .setParam("http.connection.timeout", 3000)
                        .setParam("http.socket.timeout", 3000)
                        .setParam("http.connection-manager.timeout", 3000L));

        this.requestSpecification = given().baseUri(BASE_URL);
    }

    protected Response postRequest(Object payload,String endpoint) {
        return requestSpecification.contentType(ContentType.JSON)
                .body(payload)
                .post(endpoint);
    }

    protected Response postRequest(Map<String,String> headers, String endpoint) {
        return requestSpecification.contentType(ContentType.JSON)
                .headers(headers)
                .post(endpoint);
    }

    protected Response postRequest(Object payload, Map<String,String> headers, String endpoint) {
        return requestSpecification.contentType(ContentType.JSON)
                .log().all()
                .headers(headers)
                .body(payload)
                .post(endpoint);
    }

    protected Response getRequest(String endpoint) {
        return requestSpecification.get(endpoint);
    }

    protected Response getRequest(String endpoint, Map<String, String> headers) {
        RequestSpecification spec = given().spec(requestSpecification);

        if (headers != null && !headers.isEmpty()) {
            spec.headers(headers);
        }

        return spec.get(endpoint);
    }

    protected Response patchRequest(Map<String,String> headers, Object body, String endpoint) {
        return requestSpecification.contentType(ContentType.JSON)
                .headers(headers)
                .body(body)
                .patch(endpoint);
    }

    protected Response deleteRequest(String endpoint, Map<String,String> headers) {
        return requestSpecification.headers(headers)
                .delete(endpoint);
    }
}
