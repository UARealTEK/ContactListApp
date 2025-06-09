package com.ContactList.utils.dataManagement;


import com.fasterxml.jackson.databind.ObjectMapper;

public class DataSerializer {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static String serializeUserPayload(Object payload) {
        try {
            return mapper.writeValueAsString(payload);
        } catch (Exception e) {
            throw new RuntimeException("failed to serialize an object");
        }
    }
}
